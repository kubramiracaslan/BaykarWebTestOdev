Feature: Kariyer_Baykartech_Unit_Filtering

  Scenario Outline: Anahtar kelimeye göre birimlerin listelenmesi
    Given Acik pozisyonlar sayfasindayim
    When  "<anahtar_kelime>" birim arama kutusuna yazar
    Then  icinde "<anahtar_kelime>" iceren birimler listelenir
    Examples:
      | anahtar_kelime |
      | teknoloji      |