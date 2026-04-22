# UITestSample

Kotlin Multiplatform project targeting Android, iOS.

## プロジェクト構成

* [/composeApp](./composeApp/src) は Compose Multiplatform の共有コードを配置します。
  - [commonMain](./composeApp/src/commonMain/kotlin) — Android / iOS 共通のコード
  - [androidMain](./composeApp/src/androidMain/kotlin) — Android 固有のコード
  - [iosMain](./composeApp/src/iosMain/kotlin) — iOS 固有のコード

* [/iosApp](./iosApp/iosApp) — iOS アプリのエントリーポイント（Xcode プロジェクト）

## ビルド・実行

### Android

```shell
./gradlew :composeApp:assembleDebug
```

Windows の場合は `gradlew.bat` を使用してください。

### iOS

`/iosApp` を Xcode で開いて実行するか、IDE のツールバーから実行してください。

---

## スクリーンショットテスト (Roborazzi)

[Roborazzi](https://github.com/takahirom/roborazzi) を使って Compose UI のスクリーンショットテストを実装しています。

### テスト内容

テストファイル: [`composeApp/src/androidUnitTest/.../RoborazziTest.kt`](./composeApp/src/androidUnitTest/kotlin/com/app/uitestsample/RoborazziTest.kt)

| テスト名 | 内容 |
|---------|------|
| `captureAppScreenshot` | アプリ画面の静止画スクリーンショットを PNG で保存 |
| `captureAppAnimationGif` | ボタンクリック時のアニメーションを GIF で保存 |

- **デバイス**: Pixel 7 (Robolectric)
- **SDK**: API 34 (Android 14)
- **出力先**: `composeApp/screenshots/`

### ローカルでの実行

```shell
./gradlew composeApp:recordRoborazziDebug
```

生成された画像は `composeApp/screenshots/` に出力されます。

---

## CI — PR スクリーンショット自動添付

ワークフロー: [`.github/workflows/pr-screenshot.yml`](./.github/workflows/pr-screenshot.yml)

### 動作タイミング

Pull Request の **作成・コミット追加・再オープン** 時に自動実行されます。

```
on:
  pull_request:
    types: [opened, synchronize, reopened]
```

### 処理フロー

```
PR 作成 / 更新
    ↓
Roborazzi でスクリーンショット生成
(composeApp:recordRoborazziDebug)
    ↓
screenshots ブランチの pr-{番号}/ に画像をコミット
    ↓
PR コメントにスクリーンショット一覧を投稿（更新）
```

### PR コメントのイメージ

| テスト名 | スクリーンショット |
|---------|------------------|
| captureAppScreenshot | ![example](https://placehold.co/200x400?text=PNG) |
| captureAppAnimationGif | ![example](https://placehold.co/200x400?text=GIF) |

コミットを追加するたびに同じコメントが **上書き更新** されます。

### スクリーンショットの保存場所

生成されたスクリーンショットは `screenshots` ブランチに自動コミットされます。

```
screenshots ブランチ
└── pr-{PR番号}/
    ├── captureAppScreenshot.png
    └── captureAppAnimationGif.gif
```

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)