# IRONHIDE #26956

Este repositório contém o código-fonte oficial da equipe IRONHIDE #26956 para a temporada atual da FIRST Tech Challenge (FTC) - Decode.
O robô SCORPION foi projetado para oferecer desempenho consistente em movimentação, arremesso de projéteis e coleta automática, utilizando um sistema modular em Java baseado no SDK oficial da FTC.

---

## Organização do Projeto

``` bash
MeepMeep/
├── src/main/java/br/com/user/meepmeep/
│   ├── MeepMeepTesting/            → Simulador de trajetórias Road Runner
TeamCode/
├── src/main/java/org/firstinspires/ftc/teamcode/
│   ├── drive/                      → Controle básico de movimentação
│   ├── opmodes/
│   │   ├── auto/                   → Modos autônomos
│   │   └── teleop/                 → Modos de teleoperação
│   ├── subsystems/                 → Controle dos subsistemas do robô
│   ├── tuning/                     → Ajustar valores do Road Runner
```

---

## Road Runner – Navegação Avançada

O SCORPION utiliza Road Runner para:
- Controle preciso de trajetórias durante o autônomo
- Movimentação suave com perfis de velocidade e aceleração
- Ajustes finos por meio de feedforward e localizer
- Rotinas independentes para diferentes lados do campo

---

## MeepMeep – Simulação de Trajetórias

O diretório `meepmeep/` contém um projeto separado usado para:
- Simular e visualizar rotas do Road Runner
- Testar autônomos rapidamente sem usar o robô
- Validar movimentos como "strafe", curvas e splines
- Ajustar posições iniciais e finais antes de portar para o FTC SDK

Vantagens:
- Rápido de testar
- Não gasta bateria do robô
- Evita colisões e imprecisões durante ajustes iniciais
- Ajuda a equipe a visualizar estratégias antes da etapa de programação

---

## Estrutura Modular

Cada subsistema do robô é independente e pode ser testado ou reutilizado separadamente.
Isso facilita o desenvolvimento em equipe e a manutenção do código.

| Pacote       | Função |
|:-------------|:--------|
| `drive`      | Controle dos motores principais |
| `opmodes`    | Lógica de teleop e autônomo |
| `subsystems` | Componentes físicos (Shooter, Intake etc.) |
| `meepmeep`   | Projeto usado para simulação e pré-visualização |

---

## Continuidade

Este projeto foi criado para ser reutilizado e expandido por futuras gerações da equipe IRONHIDE, oferecendo:
- Código limpo e estruturado
- Documentação clara
- Padrões de projeto consistentes
- Base de desenvolvimento para temporadas futuras

## Créditos

Equipe IRONHIDE #26956 - Decode 2025-2026

Programação: Equipe de Software

Líder de Programação: [**Daniel**](https://github.com/nielzgt)