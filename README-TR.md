<!-- Back to top -->
<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LGPL License][license-shield]][license-url]

<br />
<div align="center">
  <h1 align="center">i18label4j</h1>

  <p align="center">
    Java için esnek kimlikler ve ad alanlarıyla basit ve hafif bir kayıt kütüphanesi.<br/>
    Tipli etiketler, dinamik yer tutucular ve takılabilir serileştiricilerle uygulamanızı temiz şekilde yerelleştirin.
    <br />
    <br />
    <a href="https://github.com/leycm/i18label4j"><strong>Belgeleri inceleyin »</strong></a>
    &nbsp;·&nbsp;
    <a href="https://github.com/leycm/i18label4j/issues/new?labels=bug">Hata bildir</a>
    &nbsp;·&nbsp;
    <a href="https://github.com/leycm/i18label4j/issues/new?labels=enhancement">Özellik isteği</a>
  </p>
</div>

---

<!-- TABLE OF CONTENTS -->
<details>
  <summary>İçindekiler</summary>
  <ol>
    <li><a href="#proje-hakkinda">Proje Hakkında</a></li>
    <li>
      <a href="#baslarken">Başlarken</a>
      <ul>
        <li><a href="#on-kosullar">Ön Koşullar</a></li>
        <li><a href="#kurulum">Kurulum</a></li>
      </ul>
    </li>
    <li><a href="#kullanim">Kullanım</a></li>
    <li><a href="#mimari">Mimari</a></li>
    <li><a href="#yol-haritasi">Yol Haritası</a></li>
    <li><a href="#katkida-bulunma">Katkıda Bulunma</a></li>
    <li><a href="#lisans">Lisans</a></li>
    <li><a href="#iletisim">İletişim</a></li>
    <li><a href="#tesekkurler">Teşekkürler</a></li>
  </ol>
</details>

---

## Proje Hakkında

**i18label4j**, yerelleştirilebilir metin etiketlerini yönetmek için modüler bir Java kütüphanesidir ve temiz, akıcı bir API sunar. Sağladıkları:

- **Tipli etiketler**: yerel dile duyarlı i18n etiketleri, küresel etiketler ve değiştirilemez literal etiketleri derleme zamanında ayırt edin.
- **Yer tutucu ikamesi**: statik veya dinamik `Placeholder` nesnelerini herhangi bir etikete kaydedin ve yapılandırılabilir `PlaceholderRule` stratejileri aracılığıyla uygulayın (varsayılan olarak `${key}`, `{key}`, `%key%`, `<key>` ve daha fazlasını destekler).
- **Takılabilir serileştiriciler**: etiketleri herhangi bir hedef türe (düz `String`, Adventure `Component` vb.) dönüştürmek için `LabelSerializer` kaydedin.
- **Birden fazla yerelleştirme kaynağı**: çevirileri düz bir dizinden `DeepDirSource` ile yükleyin (dosya, kaynaklar, http ve daha fazlasını destekler) veya kendi `LocalizationSource` uygulamanızı yazın.
- **Biçim desteği**: JSON, YAML, TOML ve Java `.properties` dosyaları kutudan çıkar çıkmaz desteklenir.
- **Çeviri önbellekleme**: `CommonLabelProvider`, çevirileri yerel başına `ConcurrentHashMap` ile iş parçacığı güvenli şekilde önbelleğe alır ve açık önbellek temizleme sunar.
- **Geriye dönük seçenek**: varsayılan değere veya `!{key}` gibi bir yedek stringe düşme desteği sunar.

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

### Kullanılan Teknolojiler

- [![Java][java-badge]][java-url]
- [![Gradle][gradle-badge]][gradle-url]
- [![Lombok][lombok-badge]][lombok-url]
- [snakeyaml](https://bitbucket.org/snakeyaml/snakeyaml) · [toml4j](https://github.com/moandjiezana/toml4j) · [org.json](https://github.com/stleary/JSON-java)

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

---

## Başlarken

### Ön Koşullar

- Java 21+
- Gradle 9+ (wrapper dahil)

### Kurulum

`build.gradle.kts` dosyanıza depo ve bağımlılık ekleyin:

```kotlin
repositories {
    maven("https://leycm.github.io/repository/")
}

dependencies {
    // Sadece API (arayüze karşı derleyin)
    compileOnly("de.leycm:label4j-api:2.0.0")

    // Tam uygulama (CommonLabelProvider, FileSource, DirSource vb. içerir)
    implementation("de.leycm.label4j-impl:2.0.0")
}
```

Veya Maven ile (`pom.xml`):

```xml
<repository>
  <id>leycm-repo</id>
  <url>https://leycm.github.io/repository/</url>
</repository>

<dependency>
  <groupId>de.leycm</groupId>
  <artifactId>label4j-api</artifactId>
  <version>2.0.0</version>
  <scope>provided</scope>
</dependency>
<dependency>
  <groupId>de.leycm</groupId>
  <artifactId>label4j-impl</artifactId>
  <version>2.0.0</version>
</dependency>
```

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

---

## Kullanım

Kullanım ve nasıl başlayacağınız hakkında bilgi için
<a href="https://github.com/leycm/i18label4j"><strong>belgeleri inceleyin</strong></a>
<!-- todo: wiki -->

## Mimari

```
i18label4j
├── i18-api/          # Public API - Label, LabelProvider, Placeholder, PlaceholderRule, LabelSerializer, LocalizationSource
└── i18-impl/         # Implementation - CommonLabelProvider, LiteralLabel, LocaleLabel,
                      #                  FileSource, DirSource, FileParser, FileUtils
```

Kütüphane iki modüle ayrılmıştır, böylece downstream projeler yalnızca API'ye bağımlı olabilir ve çalışma zamanında `Instanceable.register(...)` ile uygulamaları değiştirebilir.

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

---

## Yol Haritası

- [x] Çekirdek `Label` API'si ile `LiteralLabel` ve `LocaleLabel`
- [x] `CommonLabelProvider` ile iş parçacığı güvenli çeviri önbelleği
- [x] 10'dan fazla yerleşik yer tutucu stiline sahip `MappingRule`
- [x] JSON, YAML, TOML, `.properties` desteği olan `FileSource` ve `DirSource`
- [x] Classpath (`resource://`), dosya sistemi (`file://`) ve uzak (`http(s)://`) URI şemaları
- [x] `DirSource` içinde iç içe/hiyerarşik anahtar desteği
- [x] Çeviri dosyaları için hot-reload desteği
- [ ] Derleme zamanında anahtar doğrulaması için Maven / Gradle eklentisi
- [ ] Ek serileştirici modüller (Adventure, MiniMessage)

Tam önerilen özellik listesi ve bilinen hatalar için [açık sorunları](https://github.com/leycm/i18label4j/issues) inceleyin.

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

---

## Katkıda Bulunma

Katkılar, açık kaynağı öğrenmek ve inşa etmek için harika bir yer haline getirir. Yaptığınız her katkı **çok değerli**dir.

1. Projeyi fork edin
2. Özellik dalınızı oluşturun (`git checkout -b feat/amazing-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'feat: add some amazing Features'`)
4. Dalınızı push edin (`git push origin feat/amazing-feature`)
5. Pull Request açın

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

---

## Lisans

**GNU Lesser General Public License v3.0** altında dağıtılmaktadır. Daha fazla bilgi için [`LICENSE.LGPL`](LICENSE.LGPL) dosyasına bakın.

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

---

## İletişim

**Lennard** — leycm@proton.me

Proje Bağlantısı: [https://github.com/leycm/label4j](https://github.com/leycm/i18label4j)

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

---

## Teşekkürler

- [Lombok](https://projectlombok.org/) Java için boilerplate azaltma
- [SnakeYAML](https://bitbucket.org/snakeyaml/snakeyaml) YAML parsing
- [toml4j](https://github.com/moandjiezana/toml4j) TOML parsing
- [org.json](https://github.com/stleary/JSON-java) JSON parsing
- [Adventure API](https://docs.advntr.net/) Minecraft metin bileşeni kütüphanesi
- [Best-README-Template](https://github.com/othneildrew/Best-README-Template) README yapısı ilhamı
- [Shields.io](https://shields.io) daha iyi readme'ler

<p align="right">(<a href="#readme-top">başa dön</a>)</p>

---

<!-- MARKDOWN LINKS & BADGES -->
[contributors-shield]: https://img.shields.io/github/contributors/leycm/i18label4j.svg?style=for-the-badge
[contributors-url]: https://github.com/leycm/i18label4j/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/leycm/i18label4j.svg?style=for-the-badge
[forks-url]: https://github.com/leycm/i18label4j/network/members
[stars-shield]: https://img.shields.io/github/stars/leycm/i18label4j.svg?style=for-the-badge
[stars-url]: https://github.com/leycm/i18label4j/stargazers
[issues-shield]: https://img.shields.io/github/issues/leycm/i18label4j.svg?style=for-the-badge
[issues-url]: https://github.com/leycm/i18label4j/issues
[license-shield]: https://img.shields.io/github/license/leycm/i18label4j.svg?style=for-the-badge
[license-url]: https://github.com/leycm/i18label4j/blob/master/LICENSE.LGPL

[java-badge]: https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[java-url]: https://openjdk.org/projects/jdk/21/
[gradle-badge]: https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white
[gradle-url]: https://gradle.org/
[lombok-badge]: https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge&logo=lombok&logoColor=white
[lombok-url]: https://projectlombok.org/
