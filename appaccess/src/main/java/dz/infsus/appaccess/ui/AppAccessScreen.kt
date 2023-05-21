package dz.infsus.appaccess.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dz.infsus.common.R
import dz.infsus.common.state.appAccessState.AppAccessState
import dz.infsus.common.state.appAccessState.AppAccessViewStore
import dz.infsus.common.ui.components.Button
import dz.infsus.common.ui.components.HorizontalSpacer
import dz.infsus.common.ui.components.TextField
import dz.infsus.common.ui.components.VerticalSpacer
import dz.infsus.common.ui.theme.ColorPallet
import org.koin.androidx.compose.getViewModel

@Composable
fun AppAccessScreen(
    onRegisterSuccess: () -> Unit,
    onLogInClicked: () -> Unit,
    viewStore: AppAccessViewStore = getViewModel(),
) {
    val state = viewStore()
    println("[test] ${state.appAccessSuccess}")
    LaunchedEffect(Unit){
        viewStore.isRegistered()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VerticalSpacer(value = 32.dp)

        Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.ic_logo_extended),
            contentDescription = "logo"
        )

        VerticalSpacer(value = 24.dp)

        Text(
            text = "Unlock Unique Stays and Seamless Reservations!",
            style = MaterialTheme.typography.h5.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        VerticalSpacer(value = 24.dp)

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Create Account",
            style = MaterialTheme.typography.subtitle1.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
        )

        TextField(
            onTextChanged = { viewStore.updateUsername(it) },
            label = "Username",
            placeholder = "Username",
        )

        VerticalSpacer(value = 12.dp)

        TextField(
            onTextChanged = { viewStore.updatePassword(it) },
            label = "Password",
            placeholder = "Password",
            isPassword = true,
        )

        VerticalSpacer(value = 12.dp)

        TextField(
            onTextChanged = { viewStore.updateEmail(it) },
            label = "Email",
            placeholder = "Email"
        )

        VerticalSpacer(value = 12.dp)

        TextField(
            onTextChanged = { viewStore.updatePhoneNumber(it) },
            label = "Phone Number",
            placeholder = "Phone number",
            isNumber = true,
        )


        AnimatedVisibility(visible = state.isError) {
            Column {
                VerticalSpacer(value = 12.dp)

                Text(
                    text = "Please ensure all required fields are filled correctly.",
                    style = MaterialTheme.typography.body2.copy(color = ColorPallet.systemError700),
                    textAlign = TextAlign.Center
                )
            }
        }

        VerticalSpacer(value = 32.dp)

        Button(
            isEnabled = checkIfButtonIsEnabled(state),
            modifier = Modifier.fillMaxWidth(),
            padding = PaddingValues(0.dp),
            text = "Register",
            onClick = { viewStore.signUpUser(state.username, state.password, state.email, state.phoneNumber) }
        )


        VerticalSpacer(value = 16.dp)

        Row(
            modifier = Modifier.clickable { onLogInClicked() }
        ) {
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.body2.copy(color = ColorPallet.neutral900),
            )

            HorizontalSpacer(value = 4.dp)

            Text(
                text = "Login",
                style = MaterialTheme.typography.body2.copy(color = ColorPallet.neutral900, fontWeight = FontWeight.Bold),
            )
        }

        LaunchedEffect(state.appAccessSuccess) {
            if (state.appAccessSuccess) {
                onRegisterSuccess()
            }
        }
    }
}

private fun checkIfButtonIsEnabled(state: AppAccessState) =
    state.username.isNotBlank() && state.email.isNotBlank() && state.password.isNotBlank() && state.phoneNumber.isNotBlank()
