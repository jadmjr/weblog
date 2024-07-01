import requests
import json
from faker import Faker
from faker.providers import internet
import random

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
# Modulo Faker
fake = Faker('pt_BR')

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

print(response.text)
print(response.status_code)

#Criando um Remetente. 
payload = json.dumps({
"name": fake.name(),
"email": fake.email(),
"phone": fake.msisdn(),
"documentNumber": fake.cpf(),
"senderAdress": {
    "streetName": fake.street_name(),
    "number": random.randint(1, 9999),
    "complement": "Casa "+fake.safe_color_name(),
    "city": fake.city(),
    "estate": fake.state(),
    "zipCode": fake.postcode(),
    "country": "Brasil"
}
})

response = requests.request("POST", url+"sender", headers=headers, data=payload)
response_dict = response.json() 
senderId = int(response_dict["id"])

print(response.text)
print(response.status_code)

#Criando um destinatario
payload = json.dumps({
"name": fake.name(),
"email": fake.email(),
"phone": fake.msisdn(),
"documentNumber": fake.cpf(),
"recipientAdress": {
    "streetName": fake.street_name(),
    "number": random.randint(1, 9999),
    "complement": "Casa "+fake.safe_color_name(),
    "city": fake.city(),
    "estate": fake.state(),
    "zipCode": fake.postcode(),
    "country": "Brasil"
}
})

response = requests.request("POST", url+"recipient", headers=headers, data=payload)
response_dict = response.json() 
recipientId = int(response_dict["id"])

print(response.text)
print(response.status_code)

#Criando um pacote
payload = json.dumps({
"height": 100,
"width": 50,
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