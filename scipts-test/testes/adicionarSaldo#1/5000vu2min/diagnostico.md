### Análise do Teste

#### Verificações
- **Status 200**: 100% das verificações passaram (108083 de 108083), indicando que todas as requisições retornaram status 200.

#### Erros
- **Erros**: 0% de erros (0 de 108083), indicando que não houve falhas nas requisições.

#### Dados Transferidos
- **Dados Recebidos**: 24 MB (189 kB/s)
- **Dados Enviados**: 34 MB (274 kB/s)

#### Duração das Requisições (http_req_duration)
- **Média**: 1.9s
- **Mediana**: 1.79s
- **Máximo**: 5.69s
- **p(90)**: 4.05s
- **p(95)**: 4.18s

#### Tempo de Espera (http_req_waiting)
- **Média**: 1.89s
- **Mediana**: 1.79s
- **Máximo**: 5.69s
- **p(90)**: 4.05s
- **p(95)**: 4.18s

#### Requisições por Segundo (http_reqs)
- **Total**: 108083 requisições
- **Taxa**: 862.072095/s

#### Duração das Iterações (iteration_duration)
- **Média**: 2.9s
- **Mediana**: 2.79s
- **Máximo**: 6.69s
- **p(90)**: 5.05s
- **p(95)**: 5.18s

#### Concorrência
- **Máximo de Usuários Virtuais (vus_max)**: 5000

### Conclusão
Com base nos resultados:

- **Latência**: A latência média (1.9s) e a mediana (1.79s) são muito altas, indicando que o sistema está demorando para responder. O valor máximo de 5.69s é extremamente alto e indica gargalos significativos.
- **Vazão**: A taxa de requisições por segundo (862.072095/s) é alta, mas a latência elevada sugere que o sistema está sobrecarregado.
- **Concorrência**: O teste foi executado com até 5000 usuários virtuais, e todas as iterações foram completadas sem interrupções.

#### Problema Identificado
- **Threshold Excedido**: Os thresholds definidos para 

http_req_duration

 foram excedidos, indicando que a duração das requisições está acima do limite aceitável (`p(95)<500`).

### Recomendações
- **Otimização de Desempenho**: Investigar e otimizar os pontos de gargalo no sistema para reduzir a latência das requisições.
- **Escalabilidade**: Considerar melhorias na infraestrutura para suportar melhor a carga de usuários virtuais.