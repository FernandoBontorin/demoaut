# language: pt
Funcionalidade: Reservar Ticket

  Contexto: Logado
    Dado Acessar a URL
    E Informar "UserName"
    E Informar "Password"
    Quando Clicar no botao Sign-In
    Entao Logado

  @cenario1
  Cenario: 1 Reservar 1 Ticket
    Dado Logado
    Quando Selecionar a cidade de origem - Departing From
    E Selecionar a cidade de destino - Arriving In
    E Selecionar data > que data corrente
    E Selecionar a Class
    E Selecionar Passengers = 1
    Entao Clicar no botao Continue do Flight Finder
    E Selecionar o voo
    Entao Clicar no botao Continue do Select Flight
    E Preencher First name e Last Name
    E Preencher numero do cartao
    Entao Clicar em Secure Purchase

  @cenario2
  Esquema do Cenario: 2 Criar massa de dados - Reserva
    Dado Logado
    Quando Selecionar tipo viagem "One Way"
    E Selecionar a cidade de origem - Departing From "<DepartingFrom>"
    E Selecionar a cidade de destino - Arriving In "<ArrivingIn>"
    E Selecionar data "<On>"
    E Selecionar a Class "<Class>"
    E Selecionar Passengers = <Passengers>
    Entao Clicar no botao Continue do Flight Finder
    E Selecionar o voo "<FlightNumber>"
    Entao Clicar no botao Continue do Select Flight
    E Preencher First name e Last Name
    E Preencher numero do cartao
    E Preencher o nome do passageiro "Passenger Name"
    Entao Clicar em Secure Purchase
    Exemplos:
      | DepartingFrom | ArrivingIn | On         | Class          | Passengers | FlightNumber            |
      | San Francisco | Portland   | 27/12/2019 | Economy class  | 1          | Pangaea Airlines 362    |
      | Frankfurt     | Zurich     | 25/10/2019 | Business class | 2          | Blue Skies Airlines 361 |


