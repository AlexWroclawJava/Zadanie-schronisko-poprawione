package com.example.shelterimproved.controllers;

import com.example.shelterimproved.models.Animal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    ArrayList<Animal> animals = new ArrayList<>();

    HelloController(){
        animals.add(new Animal("Azor", "Psy", "Pies Azor to super zwierzak, cieszący się dobrym zdrowiem. " +
                "Pies ten przeszedł bardzo dużo, ale obecnie jest w dobrych rękach i czeka na nowego właściciela. " +
                "Ten słodziak, ma obecnie 1 rok i 4 miesiące i cieszy się b.dobrym zdrowiem." +
                "Jego ulubionym jedzeniem są karmy Pedigree."));
        animals.add(new Animal("Burek", "Psy", "Pies Burek to super słodziak, bardzo lubi dużo biegąć. " +
                "Ma on sporo doświadczeń i również dużo podróżował z poprzednim właścicielem." +
                "Ten słodziak, ma obecnie 1 rok i 2 miesiące i jest bardzo radosnym psiakiem." +
                "Jego ulubionym jedzeniem są kości."));
        animals.add(new Animal("Rudy", "Koty", "Kot Rudy wie jak zdobyć przychylnośc. Jest kotem niezwykle inteligentym i uwielbia zabawy. " +
                "Ten kociak jest b.młody, ma obecnie pół roku i szuka dobrego nowego właściciela. Jest jednym z nielicznych kotów w schronisku, który zamiast lubi być głaskany to woli jak się go smera tylko za prawym uchem."));
        animals.add(new Animal("Szarlotka", "Koty", "Kotka Szarlotka wcześniej sporo chorowała. " +
                "Dzięki ofiarności kilku osób, udało się ją uratować. Szarlotka została także dokarmiona i obecnie jest po dłuższej terapii." +
                " W schronisku czeka już b.długo i mamy nadzieję, że pozna wkrótce swojego nowego Pana, lub nową Panią."));
        animals.add(new Animal("Feli", "Świnki", "Świnka Feli ma niesamowitą historię. Swiniak ten przypłynął do Polski z Peru, gdzie udało mu się uciec z hodowli. " +
                "W hodowli tej przeznaczono zwierzaki takie jak on na jedzenie w restauracjach. " +
                "Nie wiadomo, jak mu się udało uciec, ale wiadomo, że 12 letni chłopiec go znalazł i zabrał do Europy na wakacje do kuzyna w Polsce. " +
                "Osoba ta następnie nie była w stanie długo się zajmować Felim i przekazała go do naszego schroniska."));
        animals.add(new Animal("Pigi", "Świnki", "Pigi to samiczka świnka i ma dopiero 7 miesięcy. Lubi jeść dużo sianka i cieszy się wyśmienitym apetytem. " +
                "Pigi miała wcześniej skręconą nóżkę, ale po 2 miesięcznej rehabilitacji, wróciła do dobrego zdrowia."));
    }

    @RequestMapping("/schronisko")
    public String ListaGeneralna(@RequestParam(value="type", required=false) String type, Model model) {
        List<Animal> filteredAnimals = animals;
        if (type != null){
            List<Animal> f = new ArrayList<>();
            for (Animal a : animals) {
                System.out.println(a.getName());
                if (a.getType().equals(type)){
                    System.out.println(a.getType());
                    f.add(a);
                }
            }
            filteredAnimals = f;
        }
        model.addAttribute("pageInfo", "Zwierzaki, które szukają domu:");
        model.addAttribute("zwierz", filteredAnimals);
        return "animals";
    }

    @RequestMapping("/schronisko/{animal}")
    public String showAnimalDetails(@PathVariable String animal, Model model) {

        for (Animal a : animals) {
            if (a.getName().equals(animal)) {
                model.addAttribute("zwierzak", a);
            }
        }
        return "animalsDetails";
    }
}
