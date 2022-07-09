import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DropDown(
    onItemSelected: (String) -> Unit,
    menuItems: List<String>,
    modifier: Modifier = Modifier
) {

    val isExpanded = remember {
        mutableStateOf(false)
    }

    val selectedItem = remember {
        mutableStateOf("Select a pokemon")
    }

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .clickable { isExpanded.value = true }
            .width(200.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(selectedItem.value)
        Icon(Icons.Filled.ArrowDropDown, contentDescription = "")
        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            menuItems.forEachIndexed { index, name ->
                DropdownMenuItem(onClick = {
                    onItemSelected(menuItems[index])
                    isExpanded.value = false
                }) {
                    Text(text = name)
                }
            }
        }
    }
}

@Preview
@Composable
fun DropdownPreview() {

    val menuItems = listOf(
        "Magikarp",
        "Psyduck",
        "Charizard",
        "Bulbasaur",
        "Squirtle"
    )

    DropDown(onItemSelected = {}, menuItems = menuItems)
}
