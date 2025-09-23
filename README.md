# compose-animated-border

A library of Compose Modifiers to create border animations. I developed this library out of
curiosity about building animations with Compose Modifiers, and now, I find it both fun and exciting
to explore further 😍

## How does it work?

Under the hood, I use
the [Brush](https://developer.android.com/develop/ui/compose/graphics/draw/brush) to define how the
color is drawn.  
To animate it, I don’t regenerate the brush object on every frame. Instead, I transform the brush’s
matrix.  
This avoids recreating the brush for each frame, making the animation much more efficient. So you
don’t need to worry about performance when using these modifiers in your project.

## Installation

Add the dependency to your app `build.gradle` file, the latest version
is: [![Maven Central](https://img.shields.io/maven-central/v/io.github.lokile/compose-animated-border?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.lokile/compose-animated-border)

```
dependencies {
    implementation("io.github.lokile:compose-animated-border:latest_version")
}
```

## How to use it?

Simply apply the `animatedBorder` modifier to any composable that needs the animation:

![demo](https://github.com/user-attachments/assets/3bcf81c3-f068-42a6-bf66-bc85f36375b7)

```
import io.lokile.compose.modifiers.animatedBorder
import io.lokile.compose.modifiers.AnimatedStyle
//...

@Composable
fun MyView() {
    Text(
        modifier = Modifier
            .animatedBorder(
                duration = 1.seconds,
                borderWidth = 0.5.dp,
                colors = listOf(
                    Color.Red,
                    Color.Green,
                    Color.Blue,
                ),
                style = AnimatedStyle.Rotate
            )
            .padding(10.dp),
        text = "Hello hello"
    )
}
```

Parameters:

- `enable`: Enable/Disable the animation.
- `shape`: Shape of the animated border.
- `borderWidth`: Width of the border.
- `duration`: Time to complete one full animation cycle (shorter = faster).
- `style`: The animation style.
- `colors`: Colors used in the animated border.


## Some examples

<table>
  <thead>
    <tr>
      <th>Description</th>
      <th>Code</th>
      <th>Demo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Simple Loading</td>
      <td>
        <pre><code class="language-kotlin">Box(
    modifier = Modifier
        .animatedBorder(
            duration = 2.seconds,
            borderWidth = 2.dp,
            colors = listOf(
                Color.Blue.copy(alpha = 0.1f),
                Color.Blue.copy(alpha = 0.3f),
                Color.Blue.copy(alpha = 0.5f),
                Color.Blue.copy(alpha = 0.8f),
                Color.Transparent,
                Color.Transparent
            ),
            style = AnimatedStyle.Rotate
        )
        .size(120.dp),
    contentAlignment = Alignment.Center
) {
    Text(text = "Loading...")
}</code></pre>
      </td>
      <td>
        <img alt="simple_loading" src="https://github.com/user-attachments/assets/a4f59fdf-ae9c-4c9b-abc0-acbdbf8d894f" />
      </td>
    </tr>
    <tr>
          <td>Colorful Loading</td>
          <td>
            <pre><code class="language-kotlin">Box(
    modifier = Modifier
        .animatedBorder(
            duration = 2.seconds,
            borderWidth = 2.dp,
            colors = listOf(
                Color.Red.copy(alpha = 0.1f),
                Color.Green.copy(alpha = 0.3f),
                Color.Blue.copy(alpha = 0.5f),
                Color.Cyan.copy(alpha = 0.8f),
                Color.Transparent,
                Color.Transparent
            ),
            style = AnimatedStyle.Rotate
        )
        .size(120.dp),
    contentAlignment = Alignment.Center
) {
    Text(text = "Loading...")
}</code></pre>
          </td>
          <td>
            <img alt="simple_loading" src="https://github.com/user-attachments/assets/5299f902-0787-44c0-9c25-a4a1a43508de" />
          </td>
        </tr>
<tr>
          <td>Gradient style</td>
          <td>
            <pre><code class="language-kotlin">Box(
    modifier = Modifier
        .safeDrawingPadding()
        .padding(16.dp)
        .animatedBorder(
            duration = 2.seconds,
            borderWidth = 2.dp,
            colors = listOf(
                Color.Red.copy(alpha = 0.1f),
                Color.Green.copy(alpha = 0.3f),
                Color.Blue.copy(alpha = 0.5f),
                Color.Cyan.copy(alpha = 0.8f),
                Color.Transparent,
                Color.Transparent,
            ),
            style = AnimatedStyle.Gradient
        )
        .size(120.dp),
        contentAlignment = Alignment.Center
) {
    Text(text = "Loading...")
}</code></pre>
          </td>
          <td>
            <img alt="simple_loading" src="https://github.com/user-attachments/assets/a6811726-83cd-4def-8ca4-6c66662607aa" />
          </td>
        </tr>
  </tbody>
</table>
