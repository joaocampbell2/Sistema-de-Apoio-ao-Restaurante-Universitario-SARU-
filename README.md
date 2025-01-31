# Sistema de Apoio ao Restaurante Universitario SARU


## MEDIÇÕES DO SLA

## TESTES DE CARGA #1

### Cadastrar usuário
#### Tipo de operações: leitura / inserção

#### Arquivos envolvidos:

[AuthController](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/controller/AuthController.java)

[AuthService](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/service/auth/AuthService.java)

[AuthServiceImpl](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/service/auth/AuthServiceImpl.java)

[JwtService](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/security/JwtService.java)

[ClienteRepository](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/repository/ClienteRepository.java)


#### Data da medição: 08/01/2025

#### Descrição das configurações: I5 13400F, 16GB RAM, WINDOWS 11

#### Testes de carga (SLA):

![alt text](image.png)


### Logar Usuário

#### Tipo de operações: leitura 

#### Arquivos envolvidos:

[AuthController](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/controller/AuthController.java)

[AuthService](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/service/auth/AuthService.java)

[AuthServiceImpl](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/service/auth/AuthServiceImpl.java)

[JwtService](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/security/JwtService.java)

[ClienteRepository](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/repository/ClienteRepository.java)

#### Descrição das configurações: I5 13400F, 16GB RAM, WINDOWS 11

#### Data da medição: 08/01/2025

#### Testes de carga (SLA):
![alt text](image-1.png)

#### Data da medição: 19/01/2025

#### Testes de carga (SLA):
![media e mediana](https://github.com/user-attachments/assets/a0a1fe7f-d162-48c0-8889-77c1a982040c)
![maxima](https://github.com/user-attachments/assets/020be8a8-4fee-4af8-b6fc-0ae2a505ac84)



Melhorias: Adição de Cache para evitar o contato ao banco de dados muitas vezes

Arquivos: https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/pull/60/commits/108e6b7f3600fc9c3cafe4cfc9f87e8becb13b9e

TESTES DE CARGA #2
