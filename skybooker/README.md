# Complementação

## Arquivos `estados.txt` e `pesos-estados.jpg`

Os arquivos `estados.txt` e `pesos-estados.jpg` são utilizados pelo programa para calcular o custo das passagens entre estados.  
Cada estado foi enumerado (como é possível visualizar na imagem), permitindo que o usuário selecione um estado de partida e outro de destino.  
Com base nesses valores, o sistema identifica a distância (peso) entre eles e calcula o valor correspondente do voo.

A definição dos valores foi feita considerando:

- Maior diferença entre estados mais distantes
- Regiões com maior ou menor movimentação aérea
- Estados turísticos ou com fluxo intenso de voos

---

## 📄 estados.txt

O arquivo `estados.txt` contém a lista de estados enumerados para facilitar o processamento.



---

## 🖼️ pesos-estados.jpg

A imagem `pesos-estados.jpg` mostra visualmente os pesos (distâncias) atribuídos a cada estado.  
Esses pesos são usados para definir o custo relativo entre origem e destino.

---

## ✈️ Como funciona o cálculo

1. O usuário escolhe o estado de **partida**
2. Escolhe o estado de **destino**
3. O sistema consulta o peso correspondente
4. O programa calcula o valor estimado da passagem

---

## 🔄 Alternativas para determinar os pesos

Outras formas possíveis de gerar ou aprimorar o cálculo:

### ✔️ Algoritmos de grafos
- Uso de algoritmos como Dijkstra ou Floyd-Warshall  
- Cálculo automático de rotas mais curtas

### ✔️ Lista oficial de maior volume de voos
- Ajuste baseado em dados reais de fluxo aéreo

### ✔️ Conexões de voos
- Criação de rotas com escalas
- Opções de voos com diferentes valores e distâncias


