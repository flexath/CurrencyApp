package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flexath.currencyapp.ui.theme.CurrencyAppTheme
import com.flexath.currencyapp.ui.theme.CustomFont
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import com.flexath.currencyapp.ui.theme.currencyDimens
import com.flexath.currencyapp.ui.theme.currencyTypography
import com.flexath.currencyapp.ui.theme.getFont

@Composable
private fun BaseAppTextFiled(
    modifier: Modifier = Modifier,
    placeHolder: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    query: String,
    onValueChange: (String) -> Unit = {},
    onFocusChange: (Boolean) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = 1,
    minLines: Int = 1,
    singleLine: Boolean = true,
) {
    val onFocusChangeUpdated by rememberUpdatedState(newValue = onFocusChange)

    TextField(
        modifier = modifier.onFocusChanged { onFocusChangeUpdated(it.isFocused) },
        shape = RoundedCornerShape(MaterialTheme.currencyDimens.mediumPadding3),
        value = query,
        maxLines = maxLines,
        minLines = minLines,
        singleLine = singleLine,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = TextStyle.Default.copy(
            fontSize = MaterialTheme.currencyTypography.bodyMedium.fontSize,
            color = MaterialTheme.currencyColorScheme.colorTitleText
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.currencyColorScheme.colorBackground,
            focusedContainerColor = MaterialTheme.currencyColorScheme.colorBackground,
            disabledContainerColor = MaterialTheme.currencyColorScheme.colorBackground
        ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = {
            Text(
                text = placeHolder,
                color = MaterialTheme.currencyColorScheme.colorHint
            )
        },
        onValueChange = {
            onValueChange(it)
        }
    )
}

@Composable
fun AppSearchField(
    modifier: Modifier = Modifier,
    placeHolder: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    background: Color = Color.Unspecified,
    minLines: Int = 1,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    iconTint: Color = MaterialTheme.currencyColorScheme.colorIcon,
    query: String = "",
    onValueChange: (String) -> Unit = {},
    focusedStrokeColor: Color = MaterialTheme.colorScheme.primary,
    onSubmit: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    val defaultStrokeColor = MaterialTheme.currencyColorScheme.colorInputBoxStroke
    var isFocused by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = query) {
        isFocused = query.isNotEmpty()
    }

    val strokeColor by remember(isFocused) {
        derivedStateOf { if (isFocused) focusedStrokeColor else defaultStrokeColor }
    }

    BaseAppTextFiled(
        placeHolder = placeHolder,
        query = query,
        onValueChange = onValueChange,
        onFocusChange = { isFocused = it },
        enabled = enabled,
        readOnly = readOnly,
        minLines = minLines,
        maxLines = maxLines,
        singleLine = singleLine,
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.currencyDimens.mediumPadding3))
            .border(
                width = if (enabled) MaterialTheme.currencyDimens.smallPadding0 else 0.dp,
                color = strokeColor,
                shape = RoundedCornerShape(MaterialTheme.currencyDimens.mediumPadding3)
            )
            .background(color = background)
            .clickable {
                if (!enabled) {
                    onClick()
                }
            },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSubmit() }),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
    )
}

@Composable
fun InnerBasicTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textFieldState: TextFieldState = rememberTextFieldState(),
    hint: String,
    onKeyboardAction: (String) -> Unit = {},
    onFocusChange: (Boolean) -> Unit
) {
    val onFocusChangeUpdated by rememberUpdatedState(newValue = onFocusChange)

    if (enabled) {
        LaunchedEffect(key1 = textFieldState.text) {
            if (textFieldState.text.isEmpty()) {
                onFocusChangeUpdated(false)
            } else {
                onFocusChangeUpdated(true)
            }
        }
    }

    BasicTextField(
        state = textFieldState,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = MaterialTheme.currencyTypography.bodyMedium.copy(
            fontFamily = getFont(CustomFont.Poppins),
            color = MaterialTheme.currencyColorScheme.colorTitleText
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        onKeyboardAction = {
            onKeyboardAction(textFieldState.text.toString())
        },
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(value = MaterialTheme.colorScheme.primary),
        decorator = { innerTextField ->
            if (textFieldState.text.isEmpty()) {
                Text(
                    text = hint,
                    style = MaterialTheme.currencyTypography.bodyMedium.copy(
                        fontFamily = getFont(CustomFont.Poppins),
                    ),
                    color = MaterialTheme.currencyColorScheme.colorHint,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            innerTextField()
        },
        modifier = modifier.onFocusChanged {
            onFocusChangeUpdated(it.isFocused)
        },
    )
}

@Composable
fun SearchBasicTextField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    hint: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: Int? = null,
    leadingIconTint: Color = MaterialTheme.colorScheme.primary,
    focusedStrokeColor: Color = MaterialTheme.colorScheme.primary,
    trailingIcon: Int? = null,
    trailingIconTint: Color = MaterialTheme.colorScheme.primary,
    onClickTrailingIcon: () -> Unit = {},
    onKeyboardAction: (String) -> Unit = {}
) {
    val defaultStrokeColor = MaterialTheme.currencyColorScheme.colorInputBoxStroke
    var isFocused by remember { mutableStateOf(false) }
    val strokeColor by remember(key1 = isFocused) {
        derivedStateOf {
            if (isFocused) focusedStrokeColor else defaultStrokeColor
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.currencyDimens.mediumPadding3))
            .border(
                width = if (enabled) MaterialTheme.currencyDimens.smallPadding0 else 0.dp,
                color = strokeColor,
                shape = RoundedCornerShape(MaterialTheme.currencyDimens.mediumPadding3)
            )
            .background(color = MaterialTheme.currencyColorScheme.colorSearchField)
            .padding(vertical = MaterialTheme.currencyDimens.mediumPadding3, horizontal = MaterialTheme.currencyDimens.mediumPadding1),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    tint = leadingIconTint,
                    contentDescription = "Leading Icon",
                )
            }

            Spacer(modifier = Modifier.width(MaterialTheme.currencyDimens.mediumPadding1))

            InnerBasicTextField(
                modifier = Modifier.weight(1f),
                textFieldState = textFieldState,
                hint = hint,
                enabled = enabled,
                readOnly = readOnly,
                onKeyboardAction = onKeyboardAction,
                onFocusChange = {
                    isFocused = it
                },
            )

            Spacer(modifier = Modifier.width(MaterialTheme.currencyDimens.mediumPadding1))

            trailingIcon?.let {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    tint = trailingIconTint,
                    contentDescription = "Leading Icon",
                    modifier = Modifier
                        .clickable {
                            onClickTrailingIcon()
                        }
                )
            }
        }
    }
}

@Composable
fun EditableCustomOutlinedTextField(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    query: String,
    onQueryChange: (String) -> Unit,
    hint: String,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ),
) {
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(dimens.mediumPadding1))
            .border(
                width = dimens.smallPadding0,
                color = MaterialTheme.currencyColorScheme.colorStroke,
                shape = RoundedCornerShape(dimens.mediumPadding1)
            ).height(IntrinsicSize.Max),
        value = query,
        onValueChange = onQueryChange,
        minLines = minLines,
        maxLines = maxLines,
        singleLine = singleLine,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.currencyColorScheme.colorHint,
                maxLines = maxLines,
                overflow = TextOverflow.Visible,
                modifier = Modifier
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.currencyColorScheme.colorBackground,
            focusedContainerColor = MaterialTheme.currencyColorScheme.colorBackground,
            disabledContainerColor = MaterialTheme.currencyColorScheme.colorBackground
        ),
        keyboardOptions = keyboardOptions
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchBasicTextFieldPreview() {
    CurrencyAppTheme {
        SearchBasicTextField(
            modifier = Modifier
                .padding(
                    all = MaterialTheme.currencyDimens.largePadding2
                )
                .fillMaxWidth(),
            textFieldState = rememberTextFieldState(),
            trailingIcon = null,
            hint = "Search restaurant",
        )
    }
}