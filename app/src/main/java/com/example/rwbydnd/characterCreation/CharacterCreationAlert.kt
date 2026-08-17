package com.example.rwbydnd.characterCreation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

class CharacterCreationAlert
{
    @Composable
    fun CharacterCreatorAlert(alertText: String, onDismiss: () -> Unit)
    {
        AlertDialog(
            confirmButton = { TextButton(onClick = { onDismiss() }) {Text("Return")} },
            onDismissRequest = { onDismiss() },
            title = {Text("Missing Character Information")},
            text = {Text("Cannot continue as $alertText")}
        )
    }
}