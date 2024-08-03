# Projekt GitHub API

## Przegląd

Ten projekt to aplikacja Java Spring Boot, która współdziała z API GitHub. Umożliwia pobieranie repozytoriów użytkownika oraz szczegółów gałęzi za pomocą WebClient do asynchronicznych żądań HTTP.

## Funkcje

- Pobiera repozytoria dla danego użytkownika GitHub.
- Wyświetla gałęzie dla określonego repozytorium.
- Obsługuje niestandardowe błędy i formatowanie odpowiedzi.

## Wymagania

- Java 21 lub wyższa
- Maven
- Docker (opcjonalnie, do konteneryzacji)

## Konfiguracja

### Klonowanie Repozytorium

Wykonaj poniższe polecenia, aby sklonować repozytorium i przejść do katalogu projektu:

```bash
git clone https://github.com/yourusername/your-repo.git
cd your-repo
