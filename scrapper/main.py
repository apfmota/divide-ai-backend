import time
from selenium import webdriver # simula usuario no navegador, utilzado no webscraping
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC #trativa de erros
from selenium.common.exceptions import TimeoutException #trativa de erros
from selenium.common.exceptions import NoSuchElementException #trativa de erros
import requests #fazer requisições http
from bs4 import BeautifulSoup #webscraping
import re
import sys
import json
#validacao das urls para nao ter problema
def is_valid_url(url):
    return bool(re.match(r'^https:\/\/(www\.sefaz\.rs\.gov\.br|dfe-portal\.svrs\.rs\.gov\.br)', url))

#metodo de obtenção convencional (QR code)
def get_nfc_data(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.content, 'html.parser')
    total_value = 0
    items = []
    for item in soup.select('.linhaShade'):  
        label = item.find('label').text.strip()
        if label.lower() == 'valor a pagar r$:':
            total_value = float(item.select_one('.totalNumb').text.strip().replace(',', '.'))
            break

    for row in soup.select('tr[id^="Item"]'):  # Seleciona todas as linhas cujo id começa com "Item"
        cells = row.find_all('td')
        if len(cells) >= 2:  # Verifica se a linha tem pelo menos 2 células (nome e valor total)
            name = cells[0].text.strip().split('\r')[0]  # Pega o nome do item
            value_text = cells[-1].text.strip().replace('R$', '').replace('\r', '').replace('\n', '').replace('\t', '')  # Limpa a string de valor
            value_text = value_text.replace('Vl. Total', '')  # Remove 'Vl. Total' da string
            value = float(value_text.replace(',', '.').strip())  # Substitui vírgulas por pontos e converte para float

            quantity = cells[0].text.strip().split("Qtde.:")[1].split("UN:")[0]
            # Pega a quantidade
            quantity = quantity.replace('.', '').replace(',', '.').strip()
            unit = cells[0].text.strip().split("UN:")[1].split("Vl. Unit.:")[0]  # Pega a unidade
            items.append({'name': name, 'totalValue': value, 'quantity': quantity, 'unit': unit})
    
    storeName = soup.select_one('#u20').text.strip()
    date = soup.select_one('#infos ul li').decode_contents().split('Emissão: </strong>')[1][0:19]
    nfc = {
        'storeName': storeName,
        'date': date,
        'items': items,
        'totalValue': total_value
    }
    return nfc

#metodo de obtenção da nota diferente.
#nao sei o que precisaria mudar para pegar nome do estabelecimento e data
def get_alternative_nfc_data(url):
    options = Options()
    options.headless = True  # Configuração para rodar em modo headless
    driver = webdriver.Firefox()
    driver.get(url)
    
    # Verificar se o botão está dentro de um iframe
    try:
        iframe = driver.find_element(By.TAG_NAME, 'iframe')
        driver.switch_to.frame(iframe)
    except NoSuchElementException:
        pass  # Não há iframe, continuar normalmente
    
    # Aguardar até que o botão seja clicável
    wait = WebDriverWait(driver, 10)
    avancar_button = wait.until(EC.element_to_be_clickable((By.XPATH, "/html/body/table/tbody/tr/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[1]/input")))
    
    # Clicar no botão "Avançar"
    avancar_button.click()

    time.sleep(2)

    # Encontrar todas as tabelas com a classe NFCDetalhe_Item
    tables = driver.find_elements(By.CSS_SELECTOR, "table.NFCDetalhe_Item")
    
    items = []
    
    item_id = 1
    while True:
        try:
            item = driver.find_element(By.ID, f"Item + {item_id}")
            cells = item.find_elements(By.TAG_NAME, "td")
            if len(cells) >= 6:  # Verificar se há dados suficientes
                code = cells[0].text
                description = cells[1].text
                quantity = cells[2].text
                unit = cells[3].text
                unit_value = cells[4].text.replace(',', '.')
                total_value = cells[5].text.replace(',', '.')

                name = f"{description} (Código: {code}) Qtde.: {quantity} {unit} Vl. Unit.: {unit_value}" # transforma a para toda ali em cima em uma string so
                items.append({'name': name, 'totalValue': float(total_value), 'quantity': quantity, 'unit': unit}) # da push na lista de itens
            item_id += 1
        except NoSuchElementException:
            break  # Sai do loop quando não encontrar mais itens
        
    try:
        td_valor_pago = wait.until(EC.visibility_of_element_located((By.XPATH, "/html/body/table/tbody/tr/td/div/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td/table/tbody/tr[6]/td/table/tbody/tr[4]/td[2]")))
        total_value = float(td_valor_pago.text.replace(',', '.').strip())
    except TimeoutException:
        total_value = 0  # Defina um valor padrão ou lide com a ausência do valor final
    driver.quit()

    return items, total_value

url = sys.argv[1]
if not url or not is_valid_url(url):
    print(json.dumps({'error': 'URL inválida ou fora dos padrões permitidos.'}))

nfc = get_nfc_data(url)
if not nfc.items:
    items, total_value = get_alternative_nfc_data(url)
    nfc = {
        'storeName': '',
        'date': '',
        'items': items,
        'totalValue': total_value
    }
print(json.dumps(nfc))