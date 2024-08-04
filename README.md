# GitHub API Integration Project

## Opis projektu

Ten projekt to aplikacja Spring Boot, która integruje się z API GitHub w celu pobierania informacji o gałęziach danego repozytorium. Użytkownik podaje nazwę użytkownika GitHub oraz nazwę repozytorium, a aplikacja zwraca listę gałęzi wraz z ostatnim commit SHA.

## Wymagania

- Java 17+
- Maven 3.8+
- Konto na GitHub z wygenerowanym tokenem dostępu

## Konfiguracja

### 1. Generowanie tokenu dostępu na GitHub

Aby aplikacja mogła komunikować się z API GitHub, potrzebny jest token dostępu:

1. Zaloguj się na GitHub.
2. Przejdź do [Personal Access Tokens](https://github.com/settings/tokens).
3. Kliknij **Generate new token**.
4. Wybierz odpowiednie uprawnienia (np. `repo`).
5. Skopiuj wygenerowany token.

### 2. Ustawienie tokenu w aplikacji

Token dostępu GitHub może być przekazany do aplikacji na kilka sposobów. Zaleca się użycie zmiennych środowiskowych lub konfiguracji w pliku `application.properties`.

#### Przykład ustawienia zmiennej środowiskowej:

Na systemach Unix/Linux/MacOS:

```bash
export GITHUB_TOKEN=your_github_access_token_here

bash
Skopiuj kod
java -jar target/github-api-0.0.1-SNAPSHOT.jar
Upewnij się, że plik target/github-api-0.0.1-SNAPSHOT.jar istnieje przed uruchomieniem tego polecenia.

Konfiguracja Zmiennych Środowiskowych

Ustaw zmienną środowiskową GITHUB_TOKEN z Twoim tokenem dostępu do GitHub. Token ten jest wymagany do uwierzytelniania w API GitHub.

W systemie Linux/MacOS:

arduino
Skopiuj kod
export GITHUB_TOKEN=your_github_token
W systemie Windows (PowerShell):

ruby
Skopiuj kod
$env:GITHUB_TOKEN="your_github_token"
Konfiguracja Docker (Opcjonalnie)

Aby uruchomić aplikację w kontenerze Docker, upewnij się, że Docker jest zainstalowany i zbuduj obraz Dockera:

Skopiuj kod
docker build -t github-api-app .
Uruchom kontener Docker:

arduino
Skopiuj kod
docker run -e GITHUB_TOKEN=your_github_token -p 8080:8080 github-api-app
Zamień your_github_token na rzeczywisty token GitHub.

Endpointy
Lista Repozytoriów Użytkownika

bash
Skopiuj kod
GET /api/github/users/{userLogin}/repos
Pobiera wszystkie repozytoria dla określonego użytkownika GitHub.

Lista Gałęzi Repozytorium

bash
Skopiuj kod
GET /api/github/repos/{userLogin}/{nameOfRepo}/branches
Pobiera wszystkie gałęzie dla określonego repozytorium użytkownika GitHub.

Obsługa Błędów
Aplikacja zapewnia niestandardowe odpowiedzi błędów:

404 Not Found: Gdy użytkownik lub repozytorium nie istnieje.
400 Bad Request: Gdy wymagane parametry są brakujące lub nieprawidłowe.
Współpraca
Wszelkie wkłady są mile widziane! Proszę otworzyć zgłoszenie lub przesłać pull request z poprawkami.


Kontakt
W przypadku pytań lub opinii proszę o kontakt na adres e-mail: your-email@example.com.

