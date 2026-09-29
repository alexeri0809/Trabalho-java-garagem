# Manual do Utilizador — Programa Garagem

> 📗 Este documento é o **Manual do Utilizador**, com instruções para usar o programa. Para detalhes técnicos (arquitetura, padrões de desenho, erros e soluções), consulta o [Manual Técnico](./Manual_Tecnico.md).

---

## 1. O que é este programa

Um programa em Java que corre no terminal e simula uma **garagem com 5 lugares**. O utilizador escolhe, através de um menu, que veículos quer estacionar: **Carros**, **Barcos** ou **Motas**, em qualquer combinação (ex: 5 motas, 2 carros + 3 barcos, etc.) — o único limite é o total de 5 veículos.

Cada veículo recebe automaticamente uma **matrícula aleatória** gerada pelo programa:

- **Carros e Motas:** formato tipo `FS-13-HD`
- **Barcos:** formato tipo `6º-CO-7-15-21`

---

## 2. Antes de começar: preparar o computador

É necessário ter o **Java (JDK)** instalado.

1. Verifica se já o tens, abrindo o terminal e escrevendo:
   ```powershell
   javac -version
   ```
2. Se aparecer um número de versão (ex: `javac 21.0.x`), está tudo pronto — passa para a secção 3.
3. Se aparecer erro "não reconhecido", precisas de instalar o JDK. Se não tiveres acesso de administrador no computador, descarrega a versão em **`.zip`** (não o instalador) em [adoptium.net](https://adoptium.net/temurin/releases/), extrai para uma pasta tua (ex: `C:\Users\<utilizador>\java\jdk-21`) e adiciona ao PATH do teu utilizador:
   ```powershell
   [Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\Users\<utilizador>\java\jdk-21\bin", "User")
   ```
   Depois fecha e abre um novo terminal.

---

## 3. Compilar e correr o programa

A partir da pasta `demo` do projeto (onde está o `pom.xml`):

```powershell
Remove-Item -Recurse -Force target\classes
javac -d target\classes -encoding UTF-8 (Get-ChildItem -Recurse -Filter *.java src\main\java).FullName
java -cp target\classes com.example.Main
```

Se tudo correr bem, aparece o menu principal.

---

## 4. Como usar o menu

Ao correr o programa, aparece:

```
--- Lugares livres: 5 ---
1 - Adicionar Carro
2 - Adicionar Barco
3 - Adicionar Mota
4 - Ver garagem
5 - Ver matrículas
0 - Sair
Opção:
```

### Opções disponíveis

| Opção | O que faz |
|---|---|
| **1** | Adiciona um **Carro**. Pede a marca e o modelo; a matrícula é gerada automaticamente. |
| **2** | Adiciona um **Barco**. Pede a marca e o modelo; a matrícula é gerada automaticamente (formato marítimo). |
| **3** | Adiciona uma **Mota**. Pede a marca e o modelo; a matrícula é gerada automaticamente. |
| **4** | Mostra o estado atual da garagem, agrupado por tipo de veículo, **sem sair do programa**. |
| **5** | Mostra só a lista de matrículas de todos os veículos, pela ordem Carro → Barco → Mota. |
| **0** | Sai do programa e mostra o estado final da garagem. |

### Exemplo de utilização

```
Opção: 1
Marca: Toyota
Modelo: Corolla
Estacionado: Carro [FS-13-HD - Toyota Corolla]

Opção: 2
Marca: Quicksilver
Modelo: Activ 470
Estacionado: Barco [6º-CO-7-15-21 - Quicksilver Activ 470]

Opção: 4
Garagem (2/5)
  Carros (1)
    - Carro [FS-13-HD - Toyota Corolla]
  Barcos (1)
    - Barco [6º-CO-7-15-21 - Quicksilver Activ 470]
  Motas (0)
```

---

## 5. O que acontece quando a garagem enche

Assim que os 5 lugares estiverem ocupados, o programa deixa de aceitar novos veículos:

```
--- Lugares livres: 0 ---
...
Opção: 1
A garagem está cheia! Não é possível adicionar mais veículos.
```

Continua a ser possível usar as opções **4** (ver garagem), **5** (ver matrículas) e **0** (sair) mesmo com a garagem cheia.

---

## 6. Problemas comuns

| Sintoma | Solução |
|---|---|
| `javac`/`java` não reconhecido | Confirma que o JDK está instalado e no PATH (ver secção 2) |
| Erro ao compilar com `*.java` | Confirma que copiaste o comando corretamente, incluindo todas as barras `\` |
| Acentos aparecem trocados (ex: "Op??o" em vez de "Opção") | Usa sempre `-encoding UTF-8` ao compilar e `-Dfile.encoding=UTF-8` ao correr, como indicado na secção 3 |

Para mais detalhes técnicos sobre a arquitetura do programa, os padrões de desenho utilizados e o histórico de erros/soluções encontrados durante o desenvolvimento, consulta o **[Manual Técnico](./Manual_Tecnico.md)**.
