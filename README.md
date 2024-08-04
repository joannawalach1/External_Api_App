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
```
###Budowanie Projektu
Upewnij się, że masz zainstalowany Maven. Zbuduj projekt za pomocą Maven:
```bash
mvn clean package
```
To polecenie skompiluje kod i spakuje go do pliku JAR znajdującego się w katalogu target.

###Uruchamianie Aplikacji
Po zbudowaniu projektu możesz uruchomić aplikację za pomocą:
```bash
java -jar target/github-api-0.0.1-SNAPSHOT.jar
```
Upewnij się, że plik target/github-api-0.0.1-SNAPSHOT.jar istnieje przed uruchomieniem tego polecenia.

###Konfiguracja Tokenu GitHub
Token dostępu do GitHub jest ustawiony w pliku konfiguracyjnym application.properties lub application.yml. Upewnij się, że plik konfiguracyjny zawiera odpowiednią wartość tokenu.
Przykład application.properties:
```bash
github.token=your_github_token
```
Przykład application.yml:
```yml
github:
  token: your_github_token
```

W systemie Linux/MacOS:
```bash
export GITHUB_TOKEN=your_github_token
```

W systemie Windows (PowerShell):
```powershell
$env:GITHUB_TOKEN="your_github_token"
```


