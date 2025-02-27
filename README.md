NodeEditor README
Beschreibung
NodeEditor ist eine Java-basierte Anwendung, die es ermöglicht, Graphen zu bearbeiten und den kürzesten Weg zwischen zwei Knoten mithilfe des Dijkstra-Algorithmus zu berechnen. Die Anwendung bietet eine grafische Benutzeroberfläche (GUI), in der Benutzer Knoten und Kanten interaktiv auswählen und den Algorithmus zur Berechnung des kürzesten Pfades ausführen können.

Features
Interaktive Graphenbearbeitung:

Benutzer können Knoten auf dem Graphen auswählen und zu einem Pfad hinzufügen.
Kanten zwischen Knoten werden berücksichtigt, um sicherzustellen, dass nur verbundene Knoten ausgewählt werden können.
Der gewählte Pfad wird visuell hervorgehoben.
Dijkstra-Algorithmus:

Der Dijkstra-Algorithmus berechnet den kürzesten Weg zwischen zwei Knoten.
Der kürzeste Pfad wird farblich (blau) markiert, und die Kanten werden mit ihren Gewichten beschriftet.
Graphenwahl:

Es stehen mehrere Beispielgraphen zur Auswahl. Benutzer können den Graphen aus einem Dropdown-Menü auswählen.
Visuelle Darstellung:

Knoten und Kanten werden grafisch angezeigt.
Benutzer können den aktuellen Pfad und den kürzesten Pfad zwischen Knoten in Labels sehen.
Funktionsweise
Start der Anwendung:

Beim Starten wird ein Fenster mit einer grafischen Oberfläche angezeigt.
Der Benutzer kann einen Graphen aus dem Dropdown-Menü auswählen und mit der Bearbeitung beginnen.
Pfad auswählen:

Klicken Sie mit der linken Maustaste auf Knoten, um sie auszuwählen und zu einem Pfad hinzuzufügen.
Nur Knoten, die miteinander verbunden sind, können ausgewählt werden.
Mit der rechten Maustaste können Sie den zuletzt hinzugefügten Knoten aus dem Pfad entfernen.
Dijkstra-Algorithmus:

Klicken Sie auf den Button „Dijkstra Algorithmus starten!“, um den Dijkstra-Algorithmus auszuführen.
Der Algorithmus berechnet den kürzesten Pfad zwischen dem ersten und dem letzten Knoten im ausgewählten Pfad und gibt diesen Pfad sowie dessen Länge aus.
Anzeige der Ergebnisse:

Der gewählte Pfad wird rot hervorgehoben.
Der kürzeste Pfad wird blau markiert, und die Kanten werden entsprechend den Berechnungen des Algorithmus eingefärbt.
Der aktuelle Pfad und der kürzeste Pfad sowie deren Längen werden in Labels angezeigt.
Installation
Voraussetzungen:

Java 8 oder höher muss auf Ihrem System installiert sein.
Ausführen der Anwendung:

Stellen Sie sicher, dass alle Java-Dateien korrekt kompilieren.
Führen Sie die NodeEditor-Klasse aus, um die Anwendung zu starten.
Code-Struktur
Die Anwendung besteht aus folgenden Hauptkomponenten:

NodeEditor (Hauptklasse): Die GUI und Logik für das Laden von Graphen, das Ausführen des Dijkstra-Algorithmus und das Interagieren mit der grafischen Darstellung.

DrawingPanel: Ein Panel zum Zeichnen der Knoten und Kanten des Graphen. Es reagiert auf Mausereignisse, um Knoten auszuwählen und Kanten zu überprüfen.

Knoten und Kanten: Repräsentationen der Knoten und Kanten des Graphen. Diese Objekte beinhalten Methoden zum Setzen von Koordinaten, Gewichten und Farben sowie zur Berechnung von Entfernungen.

Beispiele und Graph: Statische Klassen, die Beispielgraphen und die zugrundeliegende Datenstruktur des Graphen verwalten.

Nutzung
Graph auswählen: Wählen Sie einen Graphen aus dem Dropdown-Menü in der oberen linken Ecke.
Knoten auswählen: Klicken Sie mit der linken Maustaste auf Knoten, um sie auszuwählen. Verbundene Knoten können zu einem Pfad hinzugefügt werden.
Pfad berechnen: Klicken Sie auf den Button „Dijkstra Algorithmus starten!“.
Pfad anzeigen: Der gewählte Pfad wird rot hervorgehoben, der kürzeste Pfad wird blau markiert.

Kontakt
Für Fragen oder Beiträge wenden Sie sich bitte an den Projektmaintainer.
