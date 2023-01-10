# Monster Trading Cards Project

## Setup
- container starten
	run --rm --detach --name swe1db -e POSTGRES_USER=swe1user -e POSTGRES_PASSWORD=swe1pw -v data:/var/lib/postgresql/data -p 5432:5432 postgres
- Datanbank erstellen
	docker exec <container ID> createdb -U swe1user mctgdb
- Datenbank befüllen mittels script.sql Datei
	docker exec -i <container ID> psql -U swe1user -d mctgdb < script.sql
- Dump erstellen und als dump.sql speichern
	docker exec -i <container ID> pg_dump -U swe1user mctgdb > dump.sql

## Design
Ganz am Anfang wollte ich mit dem groben Umfeld anfangen, hab dann aber schnell gemerkt, dass wir noch später lernen, die wir brauchen um das Projekt überhaupt umzusetzen oder zumindest leichter zu machen.
Darum hab ich mit dem Server angefangen, als es möglich war. Gleichzeitig hab ich mich um die Datenbank gekümmert, und hab hier immer parallel Schritt für Schritt gearbeitet.
Außerdem hab ich immer versucht zu beachten, dass man das Projekt theoretisch leicht erweitern kann (neue Klassen, Pfade).
Mit einigen Problem hatte ich bereits gerechnet, manche davon waren kritischer als vermutet, jedoch war ich auch teilweise überrascht, dass Manches recht schnell und einfach funktioniert hat.


## Probleme
1. Ein sehr großes Problem war die Curl Datei. Nachdem im Moodle nur die Curl Datei war und nichts dazu, hab ich zuerst viel gegoogelt, jedoch mit nicht viel Erfolg. Dass ich noch dazu Probleme beim Server selbst hatte hat nicht gerade geholfen.
2. Ein weiteres Problem war allgemein der Umgang mit Docker. Docker war ganz neu für mich und hab im ersten Moment etwas anderes hiervon erwartet. Erst gegen Ende, als ich mich auch für den CloudComputing Test vorbereitet hab, hab ich mehr verstanden. Im Umgang hab ich mit trotzdem meistens schwer getan.
3. Das fast größte Problem war das Projekt an sich. Ich hab am Anfang ganz falsch verstanden, was gefordert wird und wie das Projekt am Ende aussehen und funktionieren soll. Das hat nicht nur dazu geführt, dass ich viel Arbeit vom Anfang quasi vergessen konnte,
sondern auch, dass ich später immer noch überlegen musste, was wie genau implementiert werden muss.

## Lösungen/Herangehensweisen
1. Als aller erstes wollte ich, wie schon erwähnt, einfach mal einen funktionierenden Server haben. Als ich den hatte, musste ich ihn ändern, sodass durch ein curl auf die Datenbank voll zugegriffen werden kann.
Hierfür habe ich auf Google nicht wirklich eine eindeutige Antwort finden können. Hier war ich sehr froh, über meine hilfsbereiten Studienkolleg*innen, von denen auch ein paar Probleme mit curl hatten. Untereinander konnten ir uns dann gut helfen.
2. Wie gesagt, die Theorie dahinter konnte ich recht gut nachvollziehen, nachdem ich sie für CloudComputing gelernt hatte, in der Praxis hab ich dennoch oft Google bzw die Anleitung auf Moodle verwenden müssen.
Ich hab mich dann dazu entscheiden mit ein kleines Dokument zu schreiben, mit den wichtigsten allgemeinen commands und auch Projektspezifische für mich. Damit konnte ich dann besser oder zumindest schneller arbeiten.
3.

## Bonus Feature
Also ich bin ehrlich, sehr kreativ ist es nicht. Ich hab mir das leider fürs Ende aufgehoben, weil ich dachte, dass ich mehr Zeit dafür haben würde. Jedoch war eher das Gegenteil der Fall.
Jede 10. Kampfrunde wird eine zufällige Karte gewählt und diese bekommt zusätzlich zu ihrem eigenen Schaden noch den der gegnerischen Karte. Nach der Runde wird der Schaden wieder auf den Normalwert zurückgesetzt.

## Unit Tests
Ich habe genau 20 Unit Tests implementiert, wie mindestens vorgegeben war. Dafür habe ich darauf geachtet, dass diese wirklich sinnvoll und notwendig sind.
Angefangen hab ich mit den Karten bzw mit dem Kampfsystem. Vor allem der Besitz der Karten ist sehr wichtig für das Traden mit anderen Usern und auch für die Kämpfe selbst.
Danach hab ich passend alles rund um die Tradings erledigt, unter anderem einen Deal zu erstellen oder auch, dass man nicht mit sich selbst Traden kann.
Danach natürlich alles, was zu einem User gehört, erstellen, login und die Userdaten erhalten muss alles einwandfrei funktionieren.
Dann noch Fälle wie das erstellen von Kartenpacks, was nur ein Admin darf.
Wie gesagt, es sind "nur" 20, aber ich denke dafür sehr wichtige. Zumindest würde so einiges schieflaufen, wenn diese Funktionalitäten nicht fehlerfrei ablaufen würden, was nach einer guten Grundlage für Unit Tests für mich klingt.

## Zeitaufwand
Ich habe mit dem Projekt zwar sehr viel Zeit verbracht, aber damit hatte ich bereits gerechnet. Es war mein erster Berührungspunkt mit Java, und auch wenn die Obejektorientierung in C++ hilfreich für das erste Verständnis war, war dieses Projekt doch ein anderes Level.
Insgesamt habe ich ca 80 Stunden mit dem Projekt verbracht, den größten Teil davon in den Winterferien.

