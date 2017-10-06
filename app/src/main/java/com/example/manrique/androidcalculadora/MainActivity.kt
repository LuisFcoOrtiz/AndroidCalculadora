package com.example.manrique.androidcalculadora

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import com.example.manrique.androidcalculadora.R.id.textResultado


class MainActivity : AppCompatActivity() {

    var RESULTADOG = "resultadog"
    var MEMORIAG = "memoriag"
    var queHacer=0  //Variable para saber que hacer
    var valor1=0                    //valores para las operaciones
    var valor2=0
    var resultado=0                 //resultado de una operacion
    var memoria=0                   //Memoria de resultado
    var cambiarBinario=false        //controlador para el binario
    /*1=suma, 2=resta, 3=multiplica*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Button botonMemoria = fin
        if (savedInstanceState != null) {
            resultado=savedInstanceState.getInt("resultadog")
            memoria=savedInstanceState.getInt("memoriag")
            textResultado.text=resultado.toString()
        }//Devuelve el estado de variables en caso de cambio de actividad


    }//fin on create

    override fun onSaveInstanceState(outState: Bundle?) {
        // Guarda el estado de la vista en variables (principio de clase)
        if (outState != null) {
            outState.putInt(RESULTADOG,resultado)
            outState.putInt(MEMORIAG, memoria)
        }
        //Llama al metodo de la clase super para guardar el estado de la View
        super.onSaveInstanceState(outState)
    }//Guarda el estado de las variables

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
        mostrarMensaje("Resultado guardado en memoria: "+resultado)
    }//Asigna el resultado a la memoria

    fun devolverMemoria(v: View) {
        textResultado.text=memoria.toString()
    }//Devuelve lo guardado en memoria al text

    fun mostrarMensaje(mensaje: String) {
        val context = applicationContext
        val text = mensaje
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }//Muestra mensaje de Toast

    fun borrarMemoria(v: View) {
        mostrarMensaje("Memoria eliminada: "+memoria)
        memoria = 0
    }//Borra la memoria

    fun calcularBinario(v: View) {
        deshabilitarBotonesBinario(v)
    }//Realiza la operacion en binario

    fun deshabilitarBotonesBinario(v: View) {
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        if (cambiarBinario==false) {
            //Deshabilita los botones
            habilitarDeshabilitar(false, v)
            cambiarBinario=true

        } else if (cambiarBinario==true) {
            //Habilita los botones de nuevo
            habilitarDeshabilitar(true, v)
            cambiarBinario=false
        }
    }//deshabilita todos los botones menos el 1 y el 0

    fun habilitarDeshabilitar(hab: Boolean, v: View) {
        button2.isEnabled = hab
        button3.isEnabled = hab
        button4.isEnabled = hab
        button5.isEnabled = hab
        button6.isEnabled = hab
        button7.isEnabled = hab
        button8.isEnabled = hab
        button9.isEnabled = hab
    }

}//Fin de clase
