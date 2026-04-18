package br.com.gabifontainhas.techchallenge;

import br.com.gabifontainhas.techchallenge.entity.Address;
import br.com.gabifontainhas.techchallenge.entity.Client;
import br.com.gabifontainhas.techchallenge.entity.Owner;
import br.com.gabifontainhas.techchallenge.repository.ClientRepository;
import br.com.gabifontainhas.techchallenge.repository.OwnerRepository;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class TechchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechchallengeApplication.class, args);
	}

}
