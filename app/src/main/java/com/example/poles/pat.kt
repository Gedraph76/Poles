package com.example.poles



data class pat(val namePat:String, val gender:String,val agePat:String, val locationPat :String,
               val idNumber:String,val phonePat:String,val emailPat:String,val passwordPat:String) {
    final var thename: String
        get() :String {
            return namePat
        }

    init {
        this.thename = namePat
    }



}