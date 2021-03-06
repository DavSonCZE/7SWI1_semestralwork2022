# 7SWI1 SEMESTRÁLNÍ PROJEKT (2022)
 
### -- Téma: webová aplikace, na které mohou dávat uživatelé recenze an hry s daným score
### -- Přihlášený uživatel (user) může recenzovat hru pouze jednou. A pokud by chtěl změnit recenzi, tak pozmění staré score a text k recenzi nebo případně ji smaže. (Manipuluje maximálně se svojí recenzí)
### -- Nepříhhlášený uživatel může zobrazit recenze, ale nemůžu je bez přihlášení přidávat ři jinak s nima minipulovat.
### -- Backend: Spring Boot REST
### -- frontend: Next.Js

## SWOT
![swot_analyza](images/swot/swot_analyza.png)

## DATABASE SCHEME
![database_schema](images/database_scheme/database_diagram.drawio.png)

## ARCHITECTURE DIAGRAM:
![architecture_diagram](images/architecture/architecture_diagram.png)

## CLASS DIAGRAM: [FIX: má být z pohledu databáze]

- [User](images/class/user_class.png)
- [Game](images/class/game_class.png)
- [GameGenre](images/class/gameGenre_class.png)
- [Review](images/class/review_class.png)

![class_diagram](images/class/all_classes.png) 

## USE CASE:
- [Use cases](images/use_case/swi_project_use_cases.pdf)

![use_case](images/use_case/use_case.png)

## Analytical Sequence Diagram ( Show Games - Analytical ):
POZOR! 1.1 Ask for all games (špatný popisek)

![analytical sequence diagram](images/sequence_digrams/sequence_diagram1.drawio.png)

## Design Sequence Diagram ( Show Games - Design ):
![design sequence diagram](images/sequence_digrams/design_sequence_dialog.drawio.png)

## EPC Diagram (Add new review): [MÁ BÝT Z POHLEDU FIRMY NE IT (FIX)]
![epc diagram](images/epc_diagram/epc_diagram.png)
