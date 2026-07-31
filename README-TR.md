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
    Java i�in esnek kimlikler ve ad alanlar�yla basit ve hafif bir kay�t k�t�phanesi.<br/>
    Tipli etiketler, dinamik yer tutucular ve tak�labilir serile�tiricilerle uygulaman�z� temiz �ekilde yerelle�tirin.
    <br />
    <br />
    <a href="https://github.com/leycm/i18label4j"><strong>Belgeleri inceleyin �</strong></a>
    &nbsp;�&nbsp;
    <a href="https://github.com/leycm/i18label4j/issues/new?labels=bug">Hata bildir</a>
    &nbsp;�&nbsp;
    <a href="https://github.com/leycm/i18label4j/issues/new?labels=enhancement">�zellik iste�i</a>
  </p>
</div>

---

<!-- TABLE OF CONTENTS -->
<details>
  <summary>��indekiler</summary>
  <ol>
    <li><a href="#proje-hakkinda">Proje Hakk�nda</a></li>
    <li>
      <a href="#baslarken">Ba�larken</a>
      <ul>
        <li><a href="#on-kosullar">�n Ko�ullar</a></li>
        <li><a href="#kurulum">Kurulum</a></li>
      </ul>
    </li>
    <li><a href="#kullanim">Kullan�m</a></li>
    <li><a href="#mimari">Mimari</a></li>
    <li><a href="#yol-haritasi">Yol Haritas�</a></li>
    <li><a href="#katkida-bulunma">Katk�da Bulunma</a></li>
    <li><a href="#lisans">Lisans</a></li>
    <li><a href="#iletisim">�leti�im</a></li>
    <li><a href="#tesekkurler">Te�ekk�rler</a></li>
  </ol>
</details>

---

## Proje Hakk�nda

**i18label4j**, yerelle�tirilebilir metin etiketlerini y�netmek i�in mod�ler bir Java k�t�phanesidir ve temiz, ak�c� bir API sunar. Sa�lad�klar�:

- **Tipli etiketler**: yerel dile duyarl� i18n etiketleri, k�resel etiketler ve de�i�tirilemez literal etiketleri derleme zaman�nda ay�rt edin.
- **Yer tutucu ikamesi**: statik veya dinamik `Placeholder` nesnelerini herhangi bir etikete kaydedin ve yap�land�r�labilir `PlaceholderRule` stratejileri arac�l���yla uygulay�n (varsay�lan olarak `${key}`, `{key}`, `%key%`, `<key>` ve daha fazlas�n� destekler).
- **Tak�labilir serile�tiriciler**: etiketleri herhangi bir hedef t�re (d�z `String`, Adventure `Component` vb.) d�n��t�rmek i�in `LabelSerializer` kaydedin.
- **Birden fazla yerelle�tirme kayna��**: �evirileri d�z bir dizinden `DeepDirSource` ile y�kleyin (dosya, kaynaklar, http ve daha fazlas�n� destekler) veya kendi `LocalizationSource` uygulaman�z� yaz�n.
- **Bi�im deste�i**: JSON, YAML, TOML ve Java `.properties` dosyalar� kutudan ��kar ��kmaz desteklenir.
- **�eviri �nbellekleme**: `CommonLabelProvider`, �evirileri yerel ba��na `ConcurrentHashMap` ile i� par�ac��� g�venli �ekilde �nbelle�e al�r ve a��k �nbellek temizleme sunar.
- **Geriye d�n�k se�enek**: varsay�lan de�ere veya `!{key}` gibi bir yedek stringe d��me deste�i sunar.

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

### Kullan�lan Teknolojiler

- [![Java][java-badge]][java-url]
- [![Gradle][gradle-badge]][gradle-url]
- [![Lombok][lombok-badge]][lombok-url]
- [snakeyaml](https://bitbucket.org/snakeyaml/snakeyaml) � [toml4j](https://github.com/moandjiezana/toml4j) � [org.json](https://github.com/stleary/JSON-java)

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

---

## Ba�larken

### �n Ko�ullar

- Java 21+
- Gradle 9+ (wrapper dahil)

### Kurulum

`build.gradle.kts` dosyan�za depo ve ba��ml�l�k ekleyin:

```kotlin
repositories {
    maven("https://leycm.github.io/repository/")
}

dependencies {
    // Sadece API (aray�ze kar�� derleyin)
    compileOnly("de.leycm:label4j-api:2.0.0")

    // Tam uygulama (CommonLabelProvider, FileSource, DirSource vb. i�erir)
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

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

---

## Kullan�m

Kullan�m ve nas�l ba�layaca��n�z hakk�nda bilgi i�in
<a href="https://github.com/leycm/i18label4j"><strong>belgeleri inceleyin</strong></a>
<!-- todo: wiki -->

## Mimari

```
i18label4j
+�� i18-api/          # Public API - Label, LabelProvider, Placeholder, PlaceholderRule, LabelSerializer, LocalizationSource
L�� i18-impl/         # Implementation - CommonLabelProvider, LiteralLabel, LocaleLabel,
                      #                  FileSource, DirSource, FileParser, FileUtils
```

K�t�phane iki mod�le ayr�lm��t�r, b�ylece downstream projeler yaln�zca API'ye ba��ml� olabilir ve �al��ma zaman�nda `Instanceable.register(...)` ile uygulamalar� de�i�tirebilir.

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

---

## Yol Haritas�

- [x] �ekirdek `Label` API'si ile `LiteralLabel` ve `LocaleLabel`
- [x] `CommonLabelProvider` ile i� par�ac��� g�venli �eviri �nbelle�i
- [x] 10'dan fazla yerle�ik yer tutucu stiline sahip `MappingRule`
- [x] JSON, YAML, TOML, `.properties` deste�i olan `FileSource` ve `DirSource`
- [x] Classpath (`resource://`), dosya sistemi (`file://`) ve uzak (`http(s)://`) URI �emalar�
- [x] `DirSource` i�inde i� i�e/hiyerar�ik anahtar deste�i
- [x] �eviri dosyalar� i�in hot-reload deste�i
- [ ] Derleme zaman�nda anahtar do�rulamas� i�in Maven / Gradle eklentisi
- [ ] Ek serile�tirici mod�ller (Adventure, MiniMessage)

Tam �nerilen �zellik listesi ve bilinen hatalar i�in [a��k sorunlar�](https://github.com/leycm/i18label4j/issues) inceleyin.

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

---

## Katk�da Bulunma

Katk�lar, a��k kayna�� ��renmek ve in�a etmek i�in harika bir yer haline getirir. Yapt���n�z her katk� **�ok de�erli**dir.

1. Projeyi fork edin
2. �zellik dal�n�z� olu�turun (`git checkout -b feat/amazing-feature`)
3. De�i�ikliklerinizi commit edin (`git commit -m 'feat: add some amazing Features'`)
4. Dal�n�z� push edin (`git push origin feat/amazing-feature`)
5. Pull Request a��n

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

---

## Lisans

**GNU Lesser General Public License v3.0** alt�nda da��t�lmaktad�r. Daha fazla bilgi i�in [`LICENSE.LGPL`](LICENSE.LGPL) dosyas�na bak�n.

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

---

## �leti�im

**Lennard** � leycm@proton.me

Proje Ba�lant�s�: [https://github.com/leycm/label4j](https://github.com/leycm/i18label4j)

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

---

## Te�ekk�rler

- [Lombok](https://projectlombok.org/) Java i�in boilerplate azaltma
- [SnakeYAML](https://bitbucket.org/snakeyaml/snakeyaml) YAML ayr��t�rma
- [toml4j](https://github.com/moandjiezana/toml4j) TOML ayr��t�rma
- [org.json](https://github.com/stleary/JSON-java) JSON ayr��t�rma
- [Adventure API](https://docs.advntr.net/) Minecraft metin bile�eni k�t�phanesi
- [Best-README-Template](https://github.com/othneildrew/Best-README-Template) README yap�s� ilham�
- [Shields.io](https://shields.io) daha iyi readme'ler

<p align="right">(<a href="#readme-top">ba�a d�n</a>)</p>

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
