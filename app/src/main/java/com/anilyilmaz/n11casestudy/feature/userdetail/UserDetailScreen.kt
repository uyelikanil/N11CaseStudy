package com.anilyilmaz.n11casestudy.feature.userdetail

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anilyilmaz.n11casestudy.R
import com.anilyilmaz.n11casestudy.core.designsystem.theme.N11CaseStudyTheme
import com.anilyilmaz.n11casestudy.core.model.UserDetail

@Composable
fun UserDetailRoute(
    viewModel: UserDetailViewModel = hiltViewModel(),
    onNavigationClick: () -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UserDetailScreen(
        context = context,
        uiState = uiState,
        onFavoriteClicked = viewModel::onFavoriteClicked,
        onNavigationClick = onNavigationClick
    )
}

@Composable
private fun UserDetailScreen(
    context: Context,
    uiState: UserDetailUiState,
    onFavoriteClicked: (Long) -> Unit,
    onNavigationClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            onNavigationClick()
                        },
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = ""
                )

                if(uiState is UserDetailUiState.Success) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onFavoriteClicked(uiState.userDetail.id)
                            },
                        imageVector = if(uiState.userDetail.isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Filled.FavoriteBorder },
                        contentDescription = null
                    )
                }
            }

        }
    ) {
        when(uiState) {
            is UserDetailUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            is UserDetailUiState.Success -> {
                UserDetailLayout(
                    context = context,
                    paddingValues = it,
                    userDetail = uiState.userDetail
                )
            }
            is UserDetailUiState.Error -> {
                ErrorLayout(
                    paddingValues = it
                )
            }
        }
    }
}

@Composable
private fun UserDetailLayout(
    context: Context,
    paddingValues: PaddingValues,
    userDetail: UserDetail,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .crossfade(true)
                .data(userDetail.avatarUrl)
                .build(),
            contentDescription = "Avatar",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(CircleShape)
                .width(96.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            text = userDetail.login
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(userDetail.publicRepos.toString())
                Text(text = stringResource(R.string.public_repositories))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(userDetail.followers.toString())
                Text(text = stringResource(R.string.followers))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(userDetail.following.toString())
                Text(text = stringResource(R.string.following))
            }
        }
    }
}

@Composable
private fun ErrorLayout(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = "Error"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            text = stringResource(R.string.an_error_occurred_while_loading_user_detail)
        )
    }
}

@Preview
@Composable
private fun UserDetailScreenPreview() {
    N11CaseStudyTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            UserDetailScreen(
                context = LocalContext.current,
                uiState = UserDetailUiState.Error,
                onFavoriteClicked = {},
                onNavigationClick = {}
            )
        }
    }
}
