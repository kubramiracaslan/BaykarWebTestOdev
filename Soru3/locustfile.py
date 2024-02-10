from locust import HttpUser, task, between
            
class BaykarKariyer(HttpUser):
    host = "https://kariyer.baykartech.com"
    wait_time = between(2, 5)

    @task
    def trPage(self):
        self.client.get("/tr")

    @task
    def enPage(self):
        self.client.get("/en")

    @task
    def trAcikPozisyonlarPage(self):
        self.client.get("/tr/basvuru/acik-pozisyonlar/")

    @task
    def enAcikPozisyonlarPage(self):
        self.client.get("/en/application/open-positions/")
    
    @task
    def enAcikPozisyonlarPage(self):
        self.client.get("en/hesaplar/login/?next=/en/dashboard/")

    @task
    def enAcikPozisyonlarPage(self):
        self.client.get("tr/hesaplar/login/?next=/tr/dashboard/")