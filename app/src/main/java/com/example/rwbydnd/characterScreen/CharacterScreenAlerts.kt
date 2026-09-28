package com.example.rwbydnd.characterScreen

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

class CharacterScreenAlerts
{
    @Composable
    fun NotEnoughSkillPoints(skillPointsRequired: Int, skillPoints: Int, onDismiss: () -> Unit)
    {
        AlertDialog(
            confirmButton = { TextButton(onClick = { onDismiss() }) {Text("Return")} },
            onDismissRequest = { onDismiss() },
            title = {Text("Not Enough Skill Points")},
            text = {Text("Not enough skill points, required: $skillPointsRequired, you have $skillPoints")}
        )
    }

    @Composable
    fun SaveChanges(onDismiss: () -> Unit, onConfirmation: () -> Unit)
    {
        AlertDialog(
            title = {
                Text(text = "Save changes")
            },
            text = {
                Text(text = "Do you want to save changes made")
            },
            onDismissRequest = {
                onDismiss()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Save Changes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("Discard Changes")
                }
            }
        )
    }
}