import requests
import json
import requests
import json
from faker import Faker
from faker.providers import internet
import random


# Modulo Faker
fake = Faker('pt_BR')

url = "http://localhost:8080/"

headers = {
  'Accept': 'application/json, text/plain, */*',
  'Accept-Language': 'pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7',
  'Connection': 'keep-alive',
  'Origin': 'http://localhost:3000',
  'Referer': 'http://localhost:3000/',
  'Sec-Fetch-Dest': 'empty',
  'Sec-Fetch-Mode': 'cors',
  'Sec-Fetch-Site': 'same-site',
  'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36',
  'sec-ch-ua': '"Google Chrome";v="105", "Not)A;Brand";v="8", "Chromium";v="105"',
  'sec-ch-ua-mobile': '?0',
  'sec-ch-ua-platform': '"Windows"',
  'Content-Type': 'application/json'
}

while(True):
    #Criando um Responsável

    payload = json.dumps({
    "name": fake.name(),
    "email": fake.email(),
    "phone": fake.msisdn(),
    "documentNumber": fake.cpf(),
    "responsibleAdress": {
        "streetName": fake.street_name(),
        "number": random.randint(1, 9999),
        "complement": "Casa "+fake.safe_color_name(),
        "city": fake.city(),
        "estate": fake.state(),
        "zipCode": fake.postcode(),
        "country": "Brasil"
    }
    })

    response = requests.request("POST", url+"responsible", headers=headers, data=payload)
    response_dict = response.json() 
    responsibleId = int(response_dict["id"])
    responsibleName = response_dict["name"]

    print("olá seja bem vindo ao Weblog.")
    print("você agora é responsavél por receber as encomendas.")
    print("te chamaremos de: "+responsibleName+" e seu ID é: "+str(responsibleId)+".")

    print(responsibleName+" comece com os dados do Remetente: ")

    senderName = input('Digite o nome do remetente:')
    senderEmail = input('Digite o email do remetente:')
    senderPhone = input('Digite o telefone do remetente:')
    senderDocument = input('Digite o CPF do remetente:')

    print(responsibleName+" insira agoras os dados de endereço do Remetente: ")

    senderStreetName = input('Digite o nome da rua do remetente:')
    senderNumber = input('Digite o número da casa do remetente:')
    senderComplement = input('Digite um complemento do endereço:')
    senderCity = input('Digite o nome da cidade do remetente:')
    senderEstate = input('Digite o nome do estado do remetente:')
    senderZipCode = input('Digite o CEP do remetente:')
    senderCountry = input('Digite o Pais do remetente:')

    print(responsibleName+" agora informe os dados do Destinatário: ")

    recipientName = input('Digite o nome do destinatário:')
    recipientEmail = input('Digite o email do destinatário:')
    recipientPhone = input('Digite o telefone do destinatário:')
    recipientDocument = input('Digite o CPF do destinatário:')

    print(responsibleName+" insira agoras os dados de endereço do Destinatário: ")

    recipientStreetName = input('Digite o nome da rua do destinatário:')
    recipientNumber = input('Digite o número da casa do destinatário:')
    recipientComplement = input('Digite um complemento do endereço:')
    recipientCity = input('Digite o nome da cidade do destinatário:')
    recipientEstate = input('Digite o nome do estado do destinatário:')
    recipientZipCode = input('Digite o CEP do destinatário:')
    recipientCountry = input('Digite o Pais do destinatário:')

    #Criando um Remetente

    payload = json.dumps({
    "name": senderName,
    "email": senderEmail,
    "phone": senderPhone,
    "documentNumber": senderDocument,
    "senderAdress": {
        "streetName": senderStreetName,
        "number": senderNumber,
        "complement": senderComplement,
        "city": senderCity,
        "estate": senderEstate,
        "zipCode": senderZipCode,
        "country": senderCountry
    }
    })

    response = requests.request("POST", url+"sender", headers=headers, data=payload)
    response_dict = response.json() 
    senderId = int(response_dict["id"])

    #Criando um Destinatário

    payload = json.dumps({
    "name": recipientName,
    "email": recipientEmail,
    "phone": recipientPhone,
    "documentNumber": recipientDocument,
    "recipientAdress": {
        "streetName": recipientStreetName,
        "number": recipientNumber,
        "complement": recipientComplement,
        "city": recipientCity,
        "estate": recipientEstate,
        "zipCode": recipientZipCode,
        "country": recipientCountry
    }
    })

    response = requests.request("POST", url+"recipient", headers=headers, data=payload)
    response_dict = response.json() 
    recipientId = int(response_dict["id"])

    #Criando um pacote

    payload = json.dumps({
    "height": 100,
    "width": 100,
    "length": 20
    })

    response = requests.request("POST", url+"pack", headers=headers, data=payload)
    response_dict = response.json() 
    packId = int(response_dict["id"])

    print(response.text)
    print(response.status_code)

    #Criando uma entrega de navio 
    payload = json.dumps({
        "id": 1,
        "exitDate": None,
        "deliveryDate": None,
        "deliveryTypeId": 1,
        "deliveryStatusId": None,
        "responsibleId": responsibleId,
        "senderId": senderId,
        "recipientId": recipientId,
        "packId": packId
    })

    response = requests.request("POST", url+"delivery", headers=headers, data=payload)

    print(response.text)
    print(response.status_code)