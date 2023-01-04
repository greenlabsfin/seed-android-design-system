# Greenlabs Financial Design System

[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/com.greenlabsfin.design/core-compose.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.greenlabsfin.design%22)

그린랩스 파이낸셜의 디자인 시스템 입니다.

compose 용으로 작성 되었습니다.

## 라이브러리에 포함된 내용

### 테마

### 컴포넌트

### 사용법

#### 기본 사용
```kotlin
class MainActivity : ...
    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        setContent {
            SeedTheme {
                YourScreen()
            }
        }
    }
```
#### 커스텀 테마
```kotlin
@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = SeedColorScheme.default(darkTheme)
    val customTypoScheme = typoScheme.copy(
        headline = HeadlineTypoScheme(
            xLargeBold = TextStyle(fontSize = 1000.sp)
        )
    )
    SeedTheme(
        colorScheme = colorScheme,
        typoScheme = customTypoScheme,
        content = content,
    )
}
```
