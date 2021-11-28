package com.seriaoliwkacoding.gra2os;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean czyWygrana; //deklaracja zmiennej typu boolean - sprawdza czy gra juz sie skonczyla
    boolean checkTablica[] = new boolean[9]; //tablica czy zaznaczone
    Button tablica[] = new Button[9]; //tablica z buttonami
    String MY_PREFS_NAME; //nazwa preferencji
    Boolean kogoKolej; //zmienna która sugeruje kogo jest teraz ruch
    Boolean ktoZaczyna; //kto zacznie gre -> sharedPreferences
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        ░█▀▄░█▀▀░█░█░█░░░█▀█░█▀▄░█▀█░█▀▀░▀▀█░█▀█░░░▀█▀░█▀█░█▀▄░█░░░▀█▀░█▀▀░█░█
        ░█░█░█▀▀░█▀▄░█░░░█▀█░█▀▄░█▀█░█░░░░░█░█▀█░░░░█░░█▀█░█▀▄░█░░░░█░░█░░░░█░
        ░▀▀░░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀▀░░▀░▀░░░░▀░░▀░▀░▀▀░░▀▀▀░▀▀▀░▀▀▀░░▀░
         */
        tablica[0] = findViewById(R.id.b1); //deklaracja 1 przycisku
        tablica[1] = findViewById(R.id.b2); //deklaracja 2 przycisku
        tablica[2] = findViewById(R.id.b3); //deklaracja 3 przycisku
        tablica[3] = findViewById(R.id.b4); //deklaracja 4 przycisku
        tablica[4] = findViewById(R.id.b5); //deklaracja 5 przycisku
        tablica[5] = findViewById(R.id.b6); //deklaracja 6 przycisku
        tablica[6] = findViewById(R.id.b7); //deklaracja 7 przycisku
        tablica[7] = findViewById(R.id.b8); //deklaracja 8 przycisku
        tablica[8] = findViewById(R.id.b9); //deklaracja 9 przycisku
        czyWygrana = false; //ustawienie wygranej na false (gra sie toczy)
        for (int i = 0; i < 9;i++){ //ustawienie wartosci na false w tablicy zaznaczeń
            checkTablica[i] = false;
        }
        ktoZaczyna(); //sprawdzanie kto wygrał poprzednią grę (SharedPreferences)
    }
    /*
            ░█░█░▀█▀░█▀█░░░▀▀█░█▀█░█▀▀░▀▀█░█▀█░▀█▀░█▀▀
            ░█▀▄░░█░░█░█░░░▄▀░░█▀█░█░░░▄▀░░█░█░░█░░█▀▀
            ░▀░▀░░▀░░▀▀▀░░░▀▀▀░▀░▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀▀▀
     */
    public void ktoZaczyna(){
        TextView tekst = findViewById(R.id.zmiennanapis); //deklaracja zmiennej - napis kogo ruch
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE); //pobranie preferencji
        ktoZaczyna = prefs.getBoolean("ktoZaczyna", false);  //wczytanie zwycięzcy z poprzedniej gry
        if(!ktoZaczyna){ //jeśli pobrana preferencja jest negatywna (false)
            kogoKolej = false; //zmienienie zmiennej kogoKolej na false tak aby w funkcji opcje() wykonywał się dany if
            tekst.setText("X"); //ustawienie zmiennej przy aktualnym ruchu (wiadomość kogo kolej)
        }else{
            kogoKolej = true; //zmienienie zmiennej kogoKolej na true tak aby w funkcji opcje() wykonywał się dany if
            tekst.setText("O"); //ustawienie zmiennej przy aktualnym ruchu (wiadomość kogo kolej)
        }
    }
/*
            ░█░█░█▀█░█▀▀░█▀█░░░█░█░█▀█░█░░░█▀▀░▀▀█
            ░█▀▄░█░█░█░█░█░█░░░█▀▄░█░█░█░░░█▀▀░░░█
            ░▀░▀░▀▀▀░▀▀▀░▀▀▀░░░▀░▀░▀▀▀░▀▀▀░▀▀▀░▀▀░
 */
    public void opcje(int i){
        TextView zmienna = findViewById(R.id.zmiennanapis);
        if(!czyWygrana) {
            if (!kogoKolej){
                kogoKolej = true;
                tablica[i].setText("x");
                checkTablica[i] = true;
                tablica[i].setEnabled(false);
                zmienna.setText("O");
                czywygrana();
            }else if(kogoKolej){
                kogoKolej = false;
                tablica[i].setText("o");
                checkTablica[i] = true;
                tablica[i].setEnabled(false);
                zmienna.setText("X");
                czywygrana();
            }
        }
    }
/*
            ░█▀▀░▀▀█░█░█░░░█░█░▀█▀░█▀█
            ░█░░░▄▀░░░█░░░░█▄█░░█░░█░█
            ░▀▀▀░▀▀▀░░▀░░░░▀░▀░▀▀▀░▀░▀
 */
    public void czywygrana() {
        TextView text = findViewById(R.id.zwyklynapis);
        TextView zmiennanapis = findViewById(R.id.zmiennanapis);
        String b1 = tablica[0].getText().toString();
        String b2 = tablica[1].getText().toString();
        String b3 = tablica[2].getText().toString();
        String b4 = tablica[3].getText().toString();
        String b5 = tablica[4].getText().toString();
        String b6 = tablica[5].getText().toString();
        String b7 = tablica[6].getText().toString();
        String b8 = tablica[7].getText().toString();
        String b9 = tablica[8].getText().toString();
        if (!czyWygrana) {
            if (checkTablica[0] && b1 == b2 && b1 == b3) { //poziome 1
                ify(b1);
            } else if (checkTablica[3] && b4 == b5 && b4 == b6) { //poziome 2
                ify(b4);
            } else if (checkTablica[6] && b7 == b8 && b7 == b9) { //poziome 3
                ify(b7);
            } else if (checkTablica[0] && b1 == b4 && b1 == b7) { //pionowe 1
                ify(b7);
            } else if (checkTablica[1] && b2 == b5 && b2 == b8) { //pionowe 2
                ify(b2);
            } else if (checkTablica[2] && b3 == b6 && b3 == b9) { //pionowe 3
                ify(b3);
            }else if (checkTablica[0] && b1 == b5 && b1 == b9){ //ukos 1
                ify(b1);
            }else if (checkTablica[2] && b3 == b5 && b3 == b7){ //ukos 2
                ify(b3);
            }
        }
    }
/*
            ░█▀▄░█▀█░▀▀█░█▀█░░░█▀▄░█▀█░█▀█░█░█░█▀▀░█░█
            ░█▀▄░█▀█░▄▀░░█▀█░░░█░█░█▀█░█░█░░█░░█░░░█▀█
            ░▀▀░░▀░▀░▀▀▀░▀░▀░░░▀▀░░▀░▀░▀░▀░░▀░░▀▀▀░▀░▀
 */
    public void ify(String b) { //wykonuje się tylko jak win == true, przesyła do bazy danych kto wygrał, aby w następnej grze zaczynała ta osoba
        TextView text = findViewById(R.id.zwyklynapis);
        TextView zmiennanapis = findViewById(R.id.zmiennanapis);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        czyWygrana = true;
        text.setText("Wygrywa gracz: ");
        zmiennanapis.setText(b);
        if (b == "o"){
            prefs.edit().putBoolean("ktoZaczyna", true).apply();
        }else if (b == "x"){
            prefs.edit().putBoolean("ktoZaczyna", false).apply();
        }
    }
/*
            ░█▀█░█▀▄░▀▀█░█░█░█▀▀░▀█▀░█▀▀░█░█░▀█▀
            ░█▀▀░█▀▄░▄▀░░░█░░█░░░░█░░▀▀█░█▀▄░░█░
            ░▀░░░▀░▀░▀▀▀░░▀░░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀
 */
    public void wypelnij1(View view) { opcje(0); }
    public void wypelnij2(View view) { opcje(1); }
    public void wypelnij3(View view) { opcje(2); }
    public void wypelnij4(View view) { opcje(3); }
    public void wypelnij5(View view) { opcje(4); }
    public void wypelnij6(View view) { opcje(5); }
    public void wypelnij7(View view) { opcje(6); }
    public void wypelnij8(View view) { opcje(7); }
    public void wypelnij9(View view) { opcje(8); }

    public void restart(View view) {
        TextView text = findViewById(R.id.zwyklynapis);
        TextView zmienna = findViewById(R.id.zmiennanapis);
        for (int i = 0; i < 9;i++){
            checkTablica[i] = false;
            tablica[i].setEnabled(true);
            tablica[i].setText("");
            czyWygrana = false;
        }
        ktoZaczyna();
    }
}