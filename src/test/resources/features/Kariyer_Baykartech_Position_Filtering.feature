Feature: Kariyer_Baykartech_Position_Filtering

  Scenario Outline: Anahtar kelimeyi iceren pozisyonlarin listelenmesi
    Given Acik pozisyonlar sayfasindayim_
    When  "<anahtar_kelime>" kelimesini pozisyon arama kutusuna yazar
    Then  Basligi icinde aranan kelime iceren pozisyonlar listelenir
    Examples:
      | anahtar_kelime |
      | web            |

  Scenario Outline: Belirlenen birimler ile pozisyonlari listelenmesi
    Given Acik pozisyonlar sayfasindayim__
    When  herhangi bir "<birim>" secer
    Then  secilen birimleri iceren pozisyonlar listelenir
    Examples:
      | birim             |
      | Saha Koordinasyon |