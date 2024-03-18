package com.anilyilmaz.n11casestudy.feature.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anilyilmaz.n11casestudy.R
import com.anilyilmaz.n11casestudy.core.model.User

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onUserClick: (String?) -> Unit
) {
    val context = LocalContext.current
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val users = viewModel.searchResultUiState.collectAsLazyPagingItems()

    HomeScreen(
        context = context,
        users = users,
        searchQuery = searchQuery,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onFavoriteClicked = viewModel::onFavoriteClicked,
        onUserClick = onUserClick
    )
}

@Composable
private fun HomeScreen(
    context: Context,
    users: LazyPagingItems<User>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onFavoriteClicked: (Long) -> Unit,
    onUserClick: (String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val loadState = users.loadState.refresh

        SearchField(
            searchQuery = searchQuery,
            loadState = loadState,
            onSearchQueryChanged = onSearchQueryChanged
        )

        UserList(
            context = context,
            users = users,
            onFavoriteClicked = onFavoriteClicked,
            onUserClick = onUserClick
        )

        if(loadState is LoadState.Error) {
            val message = loadState.error.localizedMessage
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchField(
    searchQuery: String,
    loadState: LoadState,
    onSearchQueryChanged: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if(loadState is LoadState.Loading) {
                CircularProgressIndicator(modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                )
            } else if(searchQuery.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            onSearchQueryChanged("")
                    },
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.search),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_user)
            )
        },
        onValueChange = {
            onSearchQueryChanged(it)
        },
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
}

@Composable
private fun UserList(
    context: Context,
    users: LazyPagingItems<User>,
    onFavoriteClicked: (Long) -> Unit,
    onUserClick: (String?) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            users.itemCount,
            key = users.itemKey { it.id }
        ) { index ->
            val user = users[index]

            user?.let {
                ListItem(
                    modifier = Modifier
                        .clickable {
                            onUserClick(user.login)
                        },
                    leadingContent = {
                        AsyncImage(model = ImageRequest.Builder(context)
                            .crossfade(true)
                            .data(it.avatarUrl)
                            .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16))
                                .width(56.dp)
                                .height(56.dp)
                        )
                    },
                    headlineContent = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = user.login,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Left
                        )
                    },
                    trailingContent = {
                        Icon(
                            modifier = Modifier
                                .clickable {
                                    onFavoriteClicked(user.id) },
                            imageVector = if(user.isFavorite) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Filled.FavoriteBorder },
                            contentDescription = null,
                        )
                    }
                )
            }
        }
    }
}