package com.example.manrique.androidcalculadora

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    var queHacer=0  //Variable para saber que hacer
    var valor1=0
    var valor2=0
    var resultado=0
    var memoria=0
    /*1=suma, 2=resta, 3=multiplica*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }//fin on create

    fun numero(v: View) {
        val valorNumerico = findViewById<Button>(v.id)
        val textNumerico = valorNumerico.text.toString()
        //muestra los numeros que coge concadenadamente
        textResultado.text="${textResultado.text.toString()}${textNumerico}"
    }//Funcion cuando pulsas boton

    fun irGitHub(v: View) {
        val url = "https://github.com/LuisFcoOrtiz"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }//Boton para ir al desarrollador

    /*Funciones aparte*/
    fun borrarText(v: View) {
        textResultado.text=null
        resultado=0                 //Pone el resultado a 0
        queHacer=0                  //No hace nada resetea
    }//Borra toda la caja de texto
    fun deshacerText(v: View) {
        val cadena = textResultado.text.toString()
        if (cadena.length>0) {
            textResultado.text = "${cadena.substring(1)}"
        }
    }//va borrando uno a uno los caracteres

    fun operar(v: View) {
        if (textResultado.length()>0) {
            val valorDeOperacion = findViewById<Button>(v.id)
            if(valorDeOperacion.text.toString()=="+") {
                queHacer=1
            } else if(valorDeOperacion.text.toString()=="-") {
                queHacer=2
            } else if (valorDeOperacion.text.toString()=="*") {
                queHacer=3
            } else {
                queHacer = 0  //No hace nada
            }
            valor1=textResultado.text.toString().toInt()    //obtiene el valor del text
            textResultado.text=null                         //limpia el valor
        }//comprueba que no este a nulo
    }//hace la suma o la resta con el siguiente numero introducido

    fun relizarOperacion(v: View) {
        if(queHacer!=0) {
            valor2=textResultado.text.toString().toInt()    //obtiene el valor del text
            if(queHacer==1) {
                resultado = valor1+valor2
                textResultado.text="${resultado}"
            }else if(queHacer==2) {
                resultado = valor1-valor2
                textResultado.text="${resultado}"
            }else if(queHacer==3) {
                resultado = valor1*valor2
                textResultado.text="${resultado}"
            }else if (queHacer==0) {
                textResultado.text=null                     //limpia el valor
            }//comprueba que operacion hacer
        }//Comprueba que haya una operacion
        else {
            textResultado.text="0"
        }
    }//realiza la operacion final

    fun guardarMemoria(v: View) {
        memoria = resultado
        mostrarMensaje("Resultado guardado en memoria: ")
    }//Asigna el resultado a la memoria

    fun devolverMemoria(v: View) {
        textResultado.text=memoria.toString()
    }//Devuelve lo guardado en memoria al text

    fun mostrarMensaje(mensaje: String) {
        val context = applicationContext
        val text = mensaje + resultado
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }//Muestra mensaje de Toast

    fun borrarMemoria(v: View) {
        memoria = 0
        mostrarMensaje("Memoria eliminada: ")
    }//Borra la memoria

}//Fin de clase
