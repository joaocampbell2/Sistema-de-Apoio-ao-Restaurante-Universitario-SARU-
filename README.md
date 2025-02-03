# Sistema de Apoio ao Restaurante Universitario SARU


## MEDIÇÕES DO SLA

## TESTES DE CARGA #1

### Adicionar saldo

#### Tipo de operações: atualização

#### Arquivos envolvidos:

[ClienteController](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/controller/ClienteController.java)

[ClienteService](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/service/ClienteService.java)

[ClienteRepository](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/repository/ClienteRepository.java)



#### Data da medição: 03/02/2025

#### Descrição das configurações: RYZEN 5 4600G, 16GB RAM, WINDOWS 10

#### Testes de carga (SLA):

[Script de teste](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/scipts-test/scenarios/adicionarSaldo-test.js)

[Resultados](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/tree/main/scipts-test/testes/adicionarSaldo%231)

#### Hipoteses

Os resultados demonstram que existe gargalo com altas cargas.N ão existem loops ou algo que pareça aumentar significativamente a complexidade da função, ou seja, provavelmente o gargalo é causado por conta dos acessos ao banco de dados. Exisem acessos ao banco de dados desnecessarios na função, que ao serem contornados, podem gerar alguma melhora no desempenho do método.

### Logar Usuário

#### Tipo de operações: leitura 

#### Arquivos envolvidos:

[AuthController](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/controller/AuthController.java)

[AuthService](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/service/auth/AuthService.java)

[AuthServiceImpl](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/service/auth/AuthServiceImpl.java)

[JwtService](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/security/JwtService.java)

[ClienteRepository](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/saru-rest/src/main/java/saru/saru_rest/repository/ClienteRepository.java)

#### Descrição das configurações: Ryzen 5 4600g, 16GB RAM, WINDOWS 10

#### Data da medição: 31/01/2025

#### Testes de carga (SLA):

[Script de teste](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/blob/main/scipts-test/scenarios/login-test.js)

[Resultados](https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/tree/main/scipts-test/testes/login%231)

#### Data da medição: 19/01/2025

#### Testes de carga (SLA):

#### Tempo de resposta:
![media e mediana](https://github.com/user-attachments/assets/a0a1fe7f-d162-48c0-8889-77c1a982040c)

![maxima](https://github.com/user-attachments/assets/020be8a8-4fee-4af8-b6fc-0ae2a505ac84)

#### Vazão(requisições em 2 minutos):

![vazao total](https://github.com/user-attachments/assets/fb6f8fe7-6bd6-4267-97f2-ad2ea03a7fc9)

#### Concorrencia:

A concorrencia é demonstrada nos outros gráficos, no eixo debaixo. Os testes com até 3000 usuários virtuais mostraram bom desempenho, a partir disso os gargalos começaram a ser mais intensos.


#### Hipotese:

Os resultados demonstram que existe gargalo com altas cargas. Ao analisar o código, não encontramos nenhum tipo de loop ou algo que pareça aumentar significativamente a complexidade da função, ou seja, provavelmente o gargalo é causado por conta dos acessos ao banco de dados. Não consegui encontrar nenhum acesso desnecessário ao banco de dados, ou que pudesse ser contornado. Também é importante ter em mente que, no contexto do projeto e do Restaurante Universitario da UNIRIO, o resultado dos testes é satisfatorio, o sistema comporta a demanda necessária. Portanto, não consigo visualizar nenhuma mudança além da que já foi feita na primeira entrega dos testes de carga(adição do cache)

Melhorias: Adição de Cache para evitar o contato ao banco de dados muitas vezes

Arquivos: https://github.com/joaocampbell2/Sistema-de-Apoio-ao-Restaurante-Universitario-SARU-/pull/60/commits/108e6b7f3600fc9c3cafe4cfc9f87e8becb13b9e

TESTES DE CARGA #2
