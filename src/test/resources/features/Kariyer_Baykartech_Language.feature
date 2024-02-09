Feature: Kariyer_Baykartech_Language

  Scenario: Dil butonuna tiklaninca dilin degismesi kontrolu
    Given Dil testi icin sayfa acik
    And Dil butonu tiklanabilir durumda
    When Dil durumu bilgisi alinir
    And Dil butonuna tiklanir
    And Dil butonu tiklanabilir durumda
    Then Dil degisimi kontrol edilir
    And Dil butonu yazi dili kontrol edilir
    And AcikPosizyonlar buton yazi dili kontrol edilir
    And Anabasliklarin dile gore degisimi kontrol edilir
