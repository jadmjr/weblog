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

print("-------WEBLOG DELIVERY MANAGER APP-------")

print("-------ESCOLHA UM OPÇÃO-------")
print("1 - Criar nova entrega")
print("2 - Consultar uma entrega")
print("3 - Consultar um responsável")
print("4 - Consultar um remetente")
print("5 - Consultar um destinatário")

opcao = int(input())

match opcao:
    case 1:       
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
            "neighborhood": fake.neighborhood(),
            "city": fake.city(),
            "state": fake.state(),
            "zipCode": fake.postcode(),
            "country": "Brasil"
        }
        })

        response = requests.request("POST", url+"responsible", headers=headers, data=payload)
        response_dict = response.json() 
        responsibleId = int(response_dict["id"])
        responsibleName = response_dict["name"]

        print(responsibleName+" comece com os dados do Remetente: ")

        senderName = input('Digite o nome do remetente:')
        senderEmail = input('Digite o email do remetente:')
        senderPhone = input('Digite o telefone do remetente:')
        senderDocument = input('Digite o CPF do remetente:')

        print(responsibleName+" insira agoras os dados de endereço do Remetente: ")

        senderZipCode = input('Digite o CEP do remetente:')
        
        #via cep integration
        urlViaCep  = 'https://viacep.com.br/ws/'+ senderZipCode + '/json/?callback=meu_callback'
        response  = requests.request("GET", urlViaCep, headers=headers,) 
     
        responseText = (response.text);
        responseText =  responseText.replace("meu_callback(","")
        responseText =  responseText.replace(");","") 

        response_dict = json.loads(responseText)
        
        senderZipCode = response_dict["cep"]
        senderStreetName = response_dict["logradouro"]
        senderNeighborhood = response_dict["bairro"]
        senderCity = response_dict["localidade"]
        senderState = response_dict["uf"]      
        senderCountry = "BRASIL" 
        senderNumber = input('Digite o número da casa do remetente:')
        senderComplement = input('Digite um complemento do endereço:')

        print("Confirme o endereço")
        print(senderStreetName+", "+str(senderNumber)+", "+senderComplement+", "+", "+senderNeighborhood+", "+senderCity+", "+senderState+", "+senderCountry)               
        

        print(responsibleName+" insira os dados do Destinatário: ")

        recipientName = input('Digite o nome do Destinatário:')
        recipientEmail = input('Digite o email do Destinatário:')
        recipientPhone = input('Digite o telefone do Destinatário:')
        recipientDocument = input('Digite o CPF do Destinatário:')

        print(responsibleName+" insira agoras os dados de endereço do Destinatário: ")

        recipientZipCode = input('Digite o CEP do Destinatário:')
        
        #via cep integration
        urlViaCep  = 'https://viacep.com.br/ws/'+ recipientZipCode + '/json/?callback=meu_callback'
        response  = requests.request("GET", urlViaCep, headers=headers,) 
    
        responseText = (response.text);
        responseText =  responseText.replace("meu_callback(","")
        responseText =  responseText.replace(");","") 
                    
        response_dict = json.loads(responseText)
        
        recipientZipCode = response_dict["cep"]
        recipientStreetName = response_dict["logradouro"]
        recipientCity = response_dict["localidade"]
        recipientNeighborhood = response_dict["bairro"]
        recipientState = response_dict["uf"]      
        recipientCountry = "BRASIL" 
        recipientNumber = input('Digite o número da casa do remetente:')
        recipientComplement = input('Digite um complemento do endereço:')

        print("Confirme o endereço")
        print(recipientStreetName+", "+str(recipientNumber)+", "+recipientComplement+", "+", "+recipientNeighborhood+", "+recipientCity+", "+recipientState+", "+recipientCountry)             

        print(responsibleName+" insira os dados do pacote: ")

        height = float(input('Insira a altura do pacote em centimetros'))
        width =  float(input('Insira a largura do pacote em centimetros'))
        length = float(input('Insira a comprimento do pacote em centimetros'))

        print(responsibleName+"Escolha a forma de entrega do pacote: ")
        print("1 - Transporte marítimo -  R$22,00 por metro cúbico")
        print("2 - Transporte terreste -  R$35,00 por metro cúbico")
        
        tranpostMethod = int(input())

        #Criando um Remetente

        payload = json.dumps({
        "name": senderName,
        "email": senderEmail,
        "phone": senderPhone,
        "documentNumber": senderDocument,
        "senderAdress": {
            "streetName": senderStreetName,
            "number": senderNumber,
            "neighborhood": senderNeighborhood,
            "complement": senderComplement,
            "city": senderCity,
            "state": senderState,
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
            "neighborhood": recipientNeighborhood,
            "complement": recipientComplement,
            "city": recipientCity,
            "state": recipientState,
            "zipCode": recipientZipCode,
            "country": recipientCountry
        }
        })

        response = requests.request("POST", url+"recipient", headers=headers, data=payload)
        response_dict = response.json() 
        recipientId = int(response_dict["id"])

        #Criando um pacote

        payload = json.dumps({
        "height": height,
        "width": width,
        "length": length
        })

        response = requests.request("POST", url+"pack", headers=headers, data=payload)
        response_dict = response.json() 
        packId = int(response_dict["id"])

        #Criando uma entrega de navio 
        deliveryType = tranpostMethod
        payload = json.dumps({
            "id": 1,
            "exitDate": None,
            "deliveryDate": None,
            "deliveryTypeId": deliveryType,
            "deliveryStatusId": None,
            "responsibleId": responsibleId,
            "senderId": senderId,
            "recipientId": recipientId,
            "packId": packId
        })

        response = requests.request("POST", url+"delivery", headers=headers, data=payload)

        print(response.text)

        response_dict = response.json() 
        deliveryId = int(response_dict["id"])
        deliveryPostDate
        deliveryFee 
        deliveryType
        deliveryStatusId
        


