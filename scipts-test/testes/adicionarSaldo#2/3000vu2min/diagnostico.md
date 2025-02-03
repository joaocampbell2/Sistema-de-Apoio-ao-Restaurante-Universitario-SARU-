### Análise do Teste

#### Verificações
- **Status 200**: 100% das verificações passaram (98444 de 98444), indicando que todas as requisições retornaram status 200.

#### Erros
- **Erros**: 0% de erros (0 de 98444), indicando que não houve falhas nas requisições.

#### Dados Transferidos
- **Dados Recebidos**: 22 MB (175 kB/s)
- **Dados Enviados**: 31 MB (255 kB/s)

#### Duração das Requisições (http_req_duration)
- **Média**: 874.56ms
- **Mediana**: 826.78ms
- **Máximo**: 3.16s
- **p(90)**: 1.86s
- **p(95)**: 2.04s

#### Tempo de Espera (http_req_waiting)
- **Média**: 874.31ms
- **Mediana**: 826.7ms
- **Máximo**: 3.16s
- **p(90)**: 1.86s
- **p(95)**: 2.03s

#### Requisições por Segundo (http_reqs)
- **Total**: 98444 requisições
- **Taxa**: 800.19933/s

#### Duração das Iterações (iteration_duration)
- **Média**: 1.87s
- **Mediana**: 1.82s
- **Máximo**: 4.16s
- **p(90)**: 2.86s
- **p(95)**: 3.04s

#### Concorrência
- **Máximo de Usuários Virtuais (vus_max)**: 3000 

### Conclusão
Com base nos resultados:

- **Latência**: A latência média (874.56ms) e a mediana (826.78ms) são altas, indicando que o sistema está demorando mais para responder sob carga. O valor máximo de 3.16s e os percentis p(90) = 1.86s e p(95) = 2.04s indicam que há picos significativos de latência.
- **Vazão**: A taxa de requisições por segundo (800.19933/s) é alta e consistente, e não houve falhas nas requisições.
- **Concorrência**: O teste foi executado com até 3000 usuários virtuais, e todas as iterações foram completadas sem interrupções.