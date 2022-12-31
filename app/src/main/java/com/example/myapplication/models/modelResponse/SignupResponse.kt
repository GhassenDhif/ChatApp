package com.example.myapplication.modelResponse

import java.util.*

data class SignupResponse(

    var _id :String?= null,
    var Username: String?= null,
    var Email: String?= null,
    var Password: String?= null,
    var Image : String?= null,
    var Genre : String?= null,
    var Date_Naissance : Date?= null,
    var Role : String?= null,
    var Classe : String?= null,
    var Filiere : String?= null,

    var __v:Int =0

)
