package com.blackfox.myoutfitpicker.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(){
    Surface(modifier = Modifier.fillMaxWidth(),color = MaterialTheme.colors.secondary){
        Column (modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            UsernameField(inputType = KeyboardType.Email,"E-mail address")
            PasswordField(inputType = KeyboardType.Password,"Password")
            SignIn()
            ForgotPasswordText()
        }
    }
}
@Composable
fun WelcomeText(){
    Text(text = "Welcome To A-Team",
        color = Color.White,
        fontSize = 25.sp,
        modifier = Modifier.padding(top = 40.dp)
    )
}
@Composable
fun PurposeImage(){
    /*
    Image(painter = painterResource(id = R.drawable.location), contentDescription = "LocationPin",
        modifier = Modifier.size(300.dp))

     */
}
@Composable
fun UsernameField(inputType : KeyboardType, placeholder : String){
    //val ateamViewModel = getViewModel<FreelancerViewModel>()
    var textFieldEmailState = remember{ mutableStateOf("") }

    TextField(value = textFieldEmailState.value,
        onValueChange = {
            //ateamViewModel.username = it
            textFieldEmailState.value = it
        },
        leadingIcon = { Icons.Rounded.Email},
        label = { Text(text = placeholder,color = MaterialTheme.colors.primary) },
        keyboardOptions = KeyboardOptions(keyboardType = inputType),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.background)
    )
}
@Composable
fun PasswordField(inputType : KeyboardType, placeholder : String){
    //val ateamViewModel = getViewModel<FreelancerViewModel>()
    var textFieldPasswordState = remember{ mutableStateOf("") }
    TextField(value = textFieldPasswordState.value,
        onValueChange = {
            //ateamViewModel.password = it
            textFieldPasswordState.value = it
        } ,
        leadingIcon = { Icons.Outlined.Send},
        label = { Text(text = "Password",color = MaterialTheme.colors.primary) },
        keyboardOptions = KeyboardOptions(keyboardType = inputType),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colors.background)
    )
}
@Composable
fun SignIn(){
    /*
    Button(onClick = {ateamViewModel.login()},modifier = Modifier
        .padding(top = 25.dp)
        .requiredWidth(277.dp)){
        Text(text = "Sign In")
    }

     */
}
@Composable
fun ForgotPasswordText(){
    Text(text = "Forgot Password ?",color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(top = 70.dp))
}