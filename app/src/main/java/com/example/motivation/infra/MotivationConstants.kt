package com.example.motivation.infra

class MotivationConstants private constructor(){

    //classe criada para facilitar o entendimento do codigo, aqui fica claro oque esta sendo guardado

    object KEY {
        const val USER_NAME = "USER_NAME"
    }

    object FILTER {
        const val ALL = 1
        const val HAPPY = 2
        const val SUNNY = 3
    }

    object LANGUAGE {
        const val PORTUGUESE = "pt"
        const val ENGLISH = "en"
        const val FRENCH = "fr"
    }
}