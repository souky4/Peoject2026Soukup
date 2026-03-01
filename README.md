# Textová hra – Pevnost

## Popis projektu

Tento projekt je textová adventura naprogramovaná v jazyce Java.  
Hráč se pohybuje mezi místnostmi, sbírá předměty, komunikuje s postavami a snaží se splnit hlavní cíl hry.

Herní svět je načítán ze souboru `world.json`.

---

## Cíl hry

Cílem hry je najít **magický krystal** a vrátit se s ním zpět na **vchod** do pevnosti.  
Po splnění těchto podmínek hráč vyhrává.

---

## Spuštění hry

1. Otevřete projekt v IDE (např. IntelliJ IDEA).
2. Spusťte hlavní třídu `Game`.
3. Po spuštění se v konzoli zobrazí aktuální místnost.
4. Zadávejte příkazy do konzole.

---

## Ovládání hry

Příkazy se zadávají do konzole.

### Dostupné příkazy

- `jdi <směr>` – pohyb do jiné místnosti  
- `seber <id>` – sebere předmět z místnosti  
- `poloz <id>` – položí předmět do místnosti  
- `inventar` – zobrazí obsah inventáře  
- `promluv <id>` – promluví s postavou  
- `pouzij <id>` – použije předmět  
- `prozkoumat` – zobrazí informace o aktuální místnosti  
- `pomoc` – zobrazí seznam příkazů  
- `napoveda` – zobrazí herní nápovědu  
- `konec` – ukončí hru  

---

## Struktura projektu

- `Game` – hlavní třída hry  
- `World` – herní svět  
- `Room` – místnost  
- `Item` – předmět  
- `Inventory` – inventář hráče  
- `Character` / `NPC` – postavy  
- `WorldLoader` – načítání světa z JSON  
- `Command` + jednotlivé příkazy – zpracování vstupu (Command pattern)

---

## Autor

Ondřej Soukup
