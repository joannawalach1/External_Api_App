Projekt GitHub API
Przegląd
Ten projekt to aplikacja Java Spring Boot, która współdziała z API GitHub. Umożliwia pobieranie repozytoriów użytkownika oraz szczegółów gałęzi za pomocą WebClient do asynchronicznych żądań HTTP.

Funkcje
Pobiera repozytoria dla danego użytkownika GitHub.
Wyświetla gałęzie dla określonego repozytorium.
Obsługuje niestandardowe błędy i formatowanie odpowiedzi.
Wymagania
Java 21 lub wyższa
Maven
Docker (opcjonalnie, do konteneryzacji)
Konfiguracja
Klonowanie Repozytorium

Wykonaj poniższe polecenia, aby sklonować repozytorium i przejść do katalogu projektu:

bash
Skopiuj kod
git clone https://github.com/yourusername/your-repo.git
cd your-repo
Budowanie Projektu

Upewnij się, że masz zainstalowany Maven. Zbuduj projekt za pomocą Maven:

go
Skopiuj kod
mvn clean package
To polecenie skompiluje kod i spakuje go do pliku JAR znajdującego się w katalogu target.

Uruchamianie Aplikacji

Po zbudowaniu projektu możesz uruchomić aplikację za pomocą:

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

Licencja
Ten projekt jest licencjonowany na podstawie Licencji MIT - zobacz plik LICENSE dla szczegółów.

Kontakt
W przypadku pytań lub opinii proszę o kontakt na adres e-mail: your-email@example.com.
