import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonWithText(
    text: String,
    modifier: Modifier = Modifier
) {

    // initial value is false,
    // false can be changed (mutable),
    // remember the value when recomposition occurs
    val isSelected = remember {
        mutableStateOf(value = false)
    }

    Row {
        RadioButton(
            // set selected to the value stored in isSelected
            selected = isSelected.value,
            // toggle the value of isSelected
            onClick = { isSelected.value = !isSelected.value }
        )

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = modifier.padding(start = 8.dp, end = 8.dp)
        )
    }
}

@Preview
@Composable
fun RadioButtonsPreview() {
    Column {
        RadioButtonWithText(text = "Pokemon")
        Spacer(Modifier.height(24.dp))
        RadioButtonWithText(text = "Digimon")
    }
}
