package com.blueuniverse.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.time.Duration;

import com.blueuniverse.entitys.Barreira;
import com.blueuniverse.entitys.Botão;
import com.blueuniverse.entitys.Entity;
import com.blueuniverse.entitys.Killer;
import com.blueuniverse.entitys.Lili;
import com.blueuniverse.entitys.Lusion;
import com.blueuniverse.entitys.Muriel;
import com.blueuniverse.entitys.Npc1;
import com.blueuniverse.entitys.Player;
import com.blueuniverse.entitys.PortaDireita;
import com.blueuniverse.entitys.Quadro;
import com.blueuniverse.entitys.Sally;
import com.blueuniverse.graficos.Artes;
import com.blueuniverse.graficos.Spritesheet;
import com.blueuniverse.world.World;


public class Text{

	public static boolean interage = false;
	public static int curText = 0;
	public static int ativarText = 0;
	public int framesInterage = 1, maxFramesInterage = 2, indexFrames = 0, maxIndexFrames = 3;
	
	private int artAnimation = 0;
	private int artAnimationFinal = ((Game.WIDTH*Game.SCALE)/2) -((Game.ART_WIDTH)/2 +20);
	private int artAnimationEnd = 0;
	private int artAnimationEndFinal = artAnimationFinal+180;
	private boolean artAnimationEndState = false;
	public static boolean arteView = false;
	public static boolean curArte = false;
	public static int xArte = 0;
	public static int yArte = 0;
	
	public static boolean frases = false;
	public static int curFrase = 0;
	
	//dialogos zone 1
	
	public String[] itensTextPT = {"Enter ou Z para avançar.","Z para interagir e atirar.","X para atirar com dano acumulado.","C para abrir o mapa.","ESC para acessar o menu.","A chefia está te chamando Kayky..."};
	public String[] itensTextEN = {"Enter or Z to advance.","Z to interact and shoot.","X to shoot with charged damage.","C to open the map.","ESC to access the menu.","The boss is calling you, Kayky..."};
	public String[] itensTextNome = {"Druida","Druida","Druida","Druida","Druida","Druida"};
	
	public String[] itensPT = {"*Kayky aparece caminhando lentamente até a sala de sua chefe."
			,"Fui avisado que você me chamou."
			,"Houve algum problema chefe?"
			,"(Essa é Mary, a minha chefia, uma das líderes da nossa organização.)"
			,"(Uma Luxare, assim como eu.)"
			,"Tss, não sei como dizer isso de forma agradável."
			,"Então vou direto ao ponto.","A sua companheira perdeu uma ferramenta muito importante para nossa organização."
			,"…"
			,"(Fiquei sabendo que ela saiu numa missão enquanto eu estava fora…)"
			,"Quero que você recupere, é algo que não pode cair nas mãos dos nossos inimigos."
			,"Vou precisar de mais informações para isso."
			,"Retiramos algumas informações da sua companheira."
			,"A pessoa que roubou nossa ferramenta é uma bruxa invocadora."
			,"Ela possui uma aura de invocação, você vai saber quem é quando encontrar."
			,"Não se engane pela aparência dela, qualquer deslize pode levar sua vida."
			,"(Invocadores são perigosos, podem invocar monstros para o campo de batalha.)"
			,"Acha melhor enviar mais pessoas além de mim?"
			,"Não temos gente para isso, os meus subordinados estão bem longe no momento."
			,"Além do mais... Eu confio em você."
			,"Entendido, mas para onde eu devo ir?"
			,"Ela está levando a nossa ferramenta para uma cidade de bruxas, em um pântano dentro de Salém."
			,"Só sei disso, você vai ter que descobrir o resto."
			,"...Certo."
			,"Kayky!"
			,"...sim?"
			,"Essa realmente é uma missão difícil..."
			,"O estado de sua companheira é crítico..."
			,"Não morra!"
			,"(Apesar da atmosfera tensa que a chefia causa...)"
			,"(Parece que realmente se importa com seus subordinados...)"
			,"Entendido, prometo não morrer."
			,"*Kayky sai lentamente do local."};
	public String[] itensEN = {
		    "*Kayky appears, slowly walking into his boss's office.",
		    "I was told you called for me.",
		    "Was there a problem, boss?",
		    "(This is Mary, my superior, one of the leaders of our organization.)",
		    "(A Luxare, just like me.)",
		    "Tss, I don’t know how to say this nicely.",
		    "So I’ll get straight to the point.",
		    "Your partner lost a very important tool for our organization.",
		    "…",
		    "(I heard she went on a mission while I was away…)",
		    "I want you to retrieve it; it's something that cannot fall into enemy hands.",
		    "I'll need more information for that.",
		    "We got some information from your partner.",
		    "The person who stole our tool is a summoner witch.",
		    "She has a summoning aura—you’ll know when you see her.",
		    "Don’t be fooled by her appearance, any slip-up could cost you your life.",
		    "(Summoners are dangerous—they can summon monsters onto the battlefield.)",
		    "Wouldn't it be better to send more people with me?",
		    "We don’t have anyone else available. My subordinates are far away right now.",
		    "Besides... I trust you.",
		    "Understood, but where should I go?",
		    "She’s taking our tool to a witch city, in a swamp inside Salem.",
		    "That’s all I know. You’ll have to figure out the rest.",
		    "...Alright.",
		    "Kayky!",
		    "...yes?",
		    "This is truly a difficult mission…",
		    "Your partner's condition is critical…",
		    "Don’t die!",
		    "(Despite the tense atmosphere my superior gives off…)",
		    "(She really seems to care about her subordinates…)",
		    "Understood. I promise I won’t die.",
		    "*Kayky slowly leaves the place."
		};
	public String[] itensNome = {"","Kayky","Kayky"
			,"Kayky","Kayky","Mary","Mary","Mary","Kayky","Kayky","Mary","Kayky"
			,"Mary","Mary","Mary","Mary","Kayky","Kayky","Mary","Mary","Kayky","Mary"
			,"Mary","Kayky","Mary","Kayky","Mary","Mary","Mary","Kayky","Kayky","Kayky",""};
	
	public String[] kalhikoPT = {
		    "Hey Kayky!",
		    "(Esse é Kalhiko Druida, meu......... Melhor amigo?)",
		    "(Diferente de mim ele é uma espécie de metamorfo.)",
		    "(No caso, a espécie dele, é um Biota, e possui uma habilidade muito útil para espionagem.)",
		    "Consegui falar com a chefia.",
		    "Sei, mas não é isso que quero dizer.",
		    "Então?",
		    "Estou saindo em uma missão também, vou te acompanhar até a saída.",
		    "Não tenho objeções."
		};
	public String[] kalhikoEN = {
		    "Hey Kayky!",
		    "(This is Kalhiko Druid, my... best friend?)",
		    "(Unlike me, he's a kind of shapeshifter.)",
		    "(His species is called Biota, and he has a very useful skill for espionage.)",
		    "I managed to speak with the boss.",
		    "I know, but that’s not what I meant.",
		    "So?",
		    "I'm going on a mission too. I'll walk you to the exit.",
		    "I have no objections."
		};
	public String[] kalhikoNome = {"???","Kayky","Kayky",
			"Kayky","Kayky",
			"Kalhiko Druida","Kayky","Kalhiko Druida","Kayky"};
	
	public String[] kalhiko2PT = {
		    "Suas habilidades são bem diferenciadas.",
		    "Me falaram que os luxares conseguem sentir as auras de outros seres.",
		    "É mais ou menos isso mesmo.",
		    "Que interessante, bem útil aparentemente.",
		    "(De fato…)",
		    "(No universo existem incontáveis átomos e eles produzem energia.)",
		    "(Chamamos essa energia de Zéfiro e alguns seres conseguem a manipular.)",
		    "(Aqueles que podem controlar tem a possibilidade de fazer coisas extraordinárias.)",
		    "(No meu caso, eu consigo criar plasma e o disparar bem longe.)",
		    "(Também consigo ver auras, e resquícios de Zéfiro.)",
		    "(De certa forma minhas habilidades compensam minha falta de força.)",
		    "Parece que tem um boneco de teste ali."
		};
	public String[] kalhiko2EN = {
		    "Your abilities are quite unique.",
		    "I heard Luxares can sense the auras of other beings.",
		    "That's more or less true.",
		    "How interesting. Seems pretty useful.",
		    "(Indeed…)",
		    "(There are countless atoms in the universe, and they produce energy.)",
		    "(We call this energy Zéfiro, and some beings are able to manipulate it.)",
		    "(Those who can control it have the potential to do extraordinary things.)",
		    "(In my case, I can create plasma and shoot it from far away.)",
		    "(I can also see auras and traces of Zéfiro.)",
		    "(In a way, my abilities make up for my lack of physical strength.)",
		    "Looks like there's a training dummy over there."
		};
	public String[] kalhikoNome2 = {"Kalhiko Druida","Kalhiko Druida",
			"Kayky","Kalhiko Druida","Kayky","Kayky","Kayky",
			"Kayky","Kayky","Kayky","Kayky","Kalhiko Druida"};
	
	public String[] kalhiko3PT = {
		    "Mas então, me diga, o que vai fazer dessa vez?",
		    "Vou tentar recuperar um objeto misterioso em Salém.",
		    "Salém....",
		    "Fiquei sabendo que essa área está sendo atacada pelos nossos Inimigos.",
		    "(Ele está se referindo a uma organização poderosa que se opõe a nossa.)",
		    "(Satou, se não me engano eles estão causando muitas guerras pelo mundo.)",
		    "Soube que eles estão tentando reviver uma entidade antiga.",
		    "Aparentemente querem um ser poderoso para aumentar seus poderes.",
		    "Eles realmente nos odeiam, melhor tomar muito cuidado.",
		    "Realmente, porém estou preparado, não entrarei em batalhas desnecessárias.",
		    "Kayky...",
		    "HA!",
		    "Às vezes queria ser igual a você...",
		    "Não se irrita com nada e nem com ninguém.",
		    "Você nunca explode.",
		    "Diferente de mim... Sou tão... Infantil às vezes.",
		    "(Verdade, parece que ele ainda se culpa pelo nosso passado.)",
		    "(Nem sempre fomos amigos, muito pelo contrário.)",
		    "(Éramos inimigos mortais.)",
		    "Não acho isso.",
		    "Mas Kayky, se eu não tivesse sido tão impulsivo e rancoroso.",
		    "Talvez sua namorada ainda estivesse viva.",
		    "(De fato... Kalhiko matou minha namorada...)",
		    "Águas passadas, afinal eu também matei a sua.",
		    "Não guardo rancores, eram apenas tempos sombrios para nós dois.",
		    "...",
		    "HA!",
		    "Talvez seja por isso que sejamos melhores amigos.",
		    "De qualquer forma eu prometi não ficar preso no ódio novamente.",
		    "Quer dizer que eu te perdoei também.",
		    "(Dentro de cada pessoa há dores e renúncias que ninguém vê.)",
		    "(Kalhiko possui várias, e muitas fui eu quem causei.)",
		    "(Ele é um amigo valioso e por isso quero reparar meus erros do passado.)",
		    "Bem, Kalhiko, parece que iremos para caminhos diferentes agora.",
		    "Sim.",
		    "Kayky, tome cuidado, o território inimigo pode ser perigoso.",
		    "Entendido.",
		    "Até mais.",
		    "Até."
		};
	public String[] kalhiko3EN = {
		    "So, tell me, what are you going to do this time?",
		    "I'm going to try to recover a mysterious object in Salem.",
		    "Salem....",
		    "I heard that area is being attacked by our enemies.",
		    "(He's referring to a powerful organization that opposes ours.)",
		    "(Satou, if I’m not mistaken—they’ve been causing many wars around the world.)",
		    "I heard they’re trying to revive an ancient entity.",
		    "Apparently they want a powerful being to increase their power.",
		    "They really hate us. Better be very careful.",
		    "True, but I’m prepared. I won’t engage in unnecessary battles.",
		    "Kayky...",
		    "HA!",
		    "Sometimes I wish I were like you...",
		    "You don’t get irritated with anything or anyone.",
		    "You never explode.",
		    "Unlike me... I'm so... childish sometimes.",
		    "(True, it seems he still blames himself for our past.)",
		    "(We weren’t always friends—quite the opposite.)",
		    "(We were mortal enemies.)",
		    "I don’t think that’s true.",
		    "But Kayky, if I hadn’t been so impulsive and resentful...",
		    "Maybe your girlfriend would still be alive.",
		    "(Indeed... Kalhiko killed my girlfriend...)",
		    "Water under the bridge. I killed yours too, after all.",
		    "I don’t hold grudges. It was a dark time for both of us.",
		    "...",
		    "HA!",
		    "Maybe that’s why we’re best friends now.",
		    "Anyway, I promised I wouldn’t be trapped in hatred again.",
		    "That means I forgave you too.",
		    "(Inside every person there are pains and sacrifices no one sees.)",
		    "(Kalhiko carries many, and I caused a lot of them.)",
		    "(He’s a valuable friend, and that’s why I want to make up for my past mistakes.)",
		    "Well, Kalhiko, looks like we’re going our separate ways now.",
		    "Yeah.",
		    "Kayky, be careful. Enemy territory can be dangerous.",
		    "Understood.",
		    "See you.",
		    "See you."
		};
	public String[] kalhikoNome3 = {"Kalhiko Druida","Kayky",
			"Kalhiko Druida","Kalhiko Druida","Kayky",
			"Kayky","Kalhiko Druida",
			"Kalhiko Druida","Kalhiko Druida",
			"Kayky","Kalhiko Druida","Kalhiko Druida","Kalhiko Druida",
			"Kalhiko Druida","Kalhiko Druida","Kalhiko Druida",
			"Kayky","Kayky","Kayky",
			"Kayky","Kalhiko Druida","Kalhiko Druida",
			"Kayky","Kayky","Kayky",
			"Kalhiko Druida","Kalhiko Druida","Kalhiko Druida","Kalhiko Druida",
			"Kalhiko Druida","Kayky","Kayky",
			"Kayky","Kayky",
			"Kalhiko Druida","Kalhiko Druida","Kayky","Kalhiko Druida","Kayky"};
	
	//dialogos zone 2
	public String[] itensZone1PT = {
		    "*Kayky anda bem devagar pelo local, porém ele para quando ouve uma voz desconhecida.",
		    "Pare aí! Esse é meu território!",
		    "(Um humano, não, um fantasma?)",
		    "Estou apenas de passagem.",
		    "Eu sou Dujhin! Eu não aceito forasteiros nas minhas terras!",
		    "(Isso pode ser um problema...)",
		    "Se possível eu gostaria de apenas sair daqui sem conflitos.",
		    "UMA AMEAÇA???",
		    "(Não parece estar em um estado mental normal.)",
		    "Não, eu sou uma pessoa que não entra em lutas desnecessárias.",
		    "RIDÍCULO! ESSA É A MINHA CASA!",
		    "SE NÃO DER MEIA VOLTA VAI ACABAR COMO MAIS UM DOS CADÁVERES!!!",
		    "(Hostil, aparentemente, talvez eu consiga escapar sem ter de lutar com ele.)",
		    "Escute amigo, perdi algo importante, preciso passar...",
		    "ISSO É UMA AFRONTA! SEM MAIS UMA PALAVRA!!!!!"
		};
	public String[] itensZone1EN = {
		    "*Kayky walks very slowly through the area, but he stops when he hears an unfamiliar voice.",
		    "Stop right there! This is my territory!",
		    "(A human? No, a ghost?)",
		    "I'm just passing through.",
		    "I am Dujhin! I don't accept strangers on my lands!",
		    "(This could be a problem...)",
		    "If possible, I'd just like to leave here without any conflict.",
		    "A THREAT???",
		    "(He doesn’t seem to be in a normal mental state.)",
		    "No, I'm a person who doesn't engage in unnecessary fights.",
		    "RIDICULOUS! THIS IS MY HOME!",
		    "IF YOU DON’T TURN AROUND, YOU’LL END UP LIKE ONE MORE OF THE CORPSES!!!",
		    "(Hostile, apparently. Maybe I can escape without fighting him.)",
		    "Listen, friend, I lost something important, I need to pass...",
		    "THIS IS AN INSULT! NOT ANOTHER WORD!!!!!"
		};
	public String[] itensNomeZone1 = {"","???","Kayky","Kayky","Dujhin","Kayky","Kayky","Dujhin",
			"Kayky","Kayky","Dujhin","Dujhin","Kayky","Kayky","Dujhin"};
	
	public String[] itensTextlv2PT = {
		    "Você é rápido , agora estou muito irritado!",
		    "(Parece que ele realmente irá ficar me atrapalhando.)",
		    "(Eu deveria executar ele?)",
		    "(Não, não posso desperdiçar minhas energias.)",
		    "Já disse, não pretendo lutar.",
		    "A MINHA ARMADURA NÃO SABE O QUE É TRÉGUA!!!!",
		    "(O zéfiro dele emite uma aura poderosa...)",
		    "(Talvez seja um combatente corpo a corpo.)",
		    "Muito persistente...",
		    "MAS É CLARO! FANTASMAS NÃO SE CANSAM!!!",
		    "(isso explica a personalidade de minha parceira...)",
		    "(Se ele for igual a ela...)"
		};
	public String[] itensTextlv2EN = {
		    "You're fast, now I'm really pissed off!",
		    "(Looks like he’s really going to keep bothering me.)",
		    "(Should I take him out?)",
		    "(No, I can’t waste my energy.)",
		    "I’ve already said, I don’t intend to fight.",
		    "MY ARMOR DOESN’T KNOW WHAT A TRUCE IS!!!!",
		    "(His zéphiro emits a powerful aura...)",
		    "(Maybe he’s a close combat fighter.)",
		    "Very persistent...",
		    "OF COURSE! GHOSTS DON’T GET TIRED!!!",
		    "(That explains my partner's personality...)",
		    "(If he’s like her...)"
		};
	public String[] itensTextNomelv2 = {"Dujhin","Kayky","Kayky",
			"Kayky","Kayky","Dujhin",
			"Kayky","Kayky","Kayky",
			"Dujhin","Kayky","Kayky"};
	public String[] itensTextArtlv2 = {"DujhinN","KaykyN","KaykyN",
			"KaykyN","KaykyN","DujhinN",
			"KaykyN","KaykyN","KaykyC",
			"DujhinN","KaykyC","KaykyN"};
	
	public String[] itenslv3PT = {
		    "Um som estridente ressoa pelos ouvidos de Kayky.",
		    "AAAAH!!! PARE AI SEU MALDITO!!!",
		    "Para veio! Pode me deixar em paz! Já estou quase saindo da sua casa!",
		    "JAMAIS!!! ISSO É PELA MINHA HONRA!!!",
		    "(Mal sabe ele que tenho uma análise quase completa de sua personalidade.)",
		    "(Nessa altura, enfrentá-lo não seria tão inviável.)",
		    "Orgulho é apenas um passo para uma morte certeira.",
		    "Então vamos acabar com isso aqui e agora!",
		    "Não quero lutar com você.",
		    "Tarde de mais, só um de nós vai sair vivo deste local!",
		    "E ESSE SEREI EU!!!"
		};
	public String[] itenslv3EN = {
		    "A shrill sound resonates through Kayky's ears.",
		    "AAAAH!!! STOP RIGHT THERE, YOU DAMNED!!!",
		    "Stop, man! Leave me alone! I'm almost out of your house!",
		    "NEVER!!! THIS IS FOR MY HONOR!!!",
		    "(Little does he know, I have a near-complete analysis of his personality.)",
		    "(At this point, fighting him wouldn't be that unfeasible.)",
		    "Pride is just a step towards certain death.",
		    "Then let’s end this here and now!",
		    "I don't want to fight you.",
		    "Too late, only one of us will leave this place alive!",
		    "AND IT WILL BE ME!!!"
		};
	public String[] itensNomelv3 = {"","Dujhin","Kayky",
			"Dujhin","Kayky","Kayky",
			"Kayky","Dujhin","Kayky","Dujhin",
			"Dujhin"};
	
	public String[] itenslv3deathPT = {
		    "*A armadura ambulante agora se fragmenta pelo solo.",
		    "Por que foi tão longe?",
		    "Essa é minha casa, a única coisa que me resta...",
		    "(Parece que não consegue ver a diferença de nosso nível de poder...)",
		    "Não me arrependo, não é errado proteger o que se ama...",
		    "Não é mesmo, tem o meu respeito.",
		    "Não... Não preciso dele...",
		    "Descanse em paz.",
		    "...",
		    "*Kayky caminha lentamente para longe do local."
		};
	public String[] itenslv3deathEN = {
		    "*The walking armor now fragments across the ground.",
		    "Why did it go so far?",
		    "This is my home, the only thing I have left...",
		    "(Seems like he can't see the difference in our power levels...)",
		    "I don't regret it, it's not wrong to protect what you love...",
		    "Isn't that right, you have my respect.",
		    "No... I don't need it...",
		    "Rest in peace.",
		    "...",
		    "*Kayky walks slowly away from the place."
		};
	public String[] itensNomelv3death = {"","Kayky","Dujhin",
			"Kayky","Dujhin","Kayky",
			"Dujhin","Kayky","Dujhin",""};
	
	//dialogos zone 3
	public String[] itenslv4PT = {
		    "*Caminhando pela caverna, Kayky encontra uma pessoa suspeita.",
		    "Uma aura de invocação... Então é você.",
		    "(Certamente é um zéfiro poderoso, bem maior que o meu.)",
		    "Me procurando?",
		    "Não me lembro de ter irritado alguém como você.",
		    "(Realmente não irritou, se possível é melhor evitar mais um confronto.)",
		    "(Talvez ela se disponha a ouvir se souber que tem mais gente envolvida.)",
		    "Não tenho nada contra ti, mas tenho ordens para cumprir.",
		    "Já entendi, você quer recuperar o que peguei daquela garota.",
		    "(Inteligente ela aparentemente...)",
		    "Exatamente, essa é minha missão.",
		    "Não pretendo devolver.",
		    "(Talvez um blefe ajude nessa situação.)",
		    "Não seja teimosa, me entregue isso de uma vez.",
		    "Me subestimando?",
		    "Volte, se tentar me perseguir não vai encontrar nada além da morte.",
		    "(Não deu certo, talvez uma abordagem mais vitimista seja mais eficiente...)",
		    "Para veio, se eu voltar sem isso vou encontrar algo pior que a morte.",
		    "Considere isso um aviso.",
		    "(As vezes esqueço que as bruxas são orgulhosas e narcisistas.)",
		    "(Uma abordagem desse tipo não irá obter a empatia dela.)",
		    "Fui avisado de seu nível de ameaça, por isso me escolheram.",
		    "Não caio em um blefe desses, consigo ver a diferença em nossa quantidade de poder.",
		    "...",
		    "(Não sabia que invocadores podiam ver auras também...)",
		    "(Pouco provável, talvez alguma outra técnica...)",
		    "(Droga, se eu pensar demais ela vai suspeitar...)",
		    "(Não consigo analisar a personalidade dela ainda...)",
		    "Vai ficar ai me observando por quanto tempo?",
		    "(Preciso de mais informações pra convencer ela...)",
		    "Você disse que pegou isso de uma garota, eu gostaria de saber como fez isso...",
		    ".....Ara.",
		    "Ah sim!",
		    "Aquela tola, tive que bater nela com bastante força para que soltasse essa caixa.",
		    "(Violenta aparentemente.)",
		    "Ela era minha colega de trabalho.",
		    "Então eu te irritei mesmo não é?",
		    "(Certamente não gosto que machuquem meus amigos.)",
		    "(A chefia disse que ela estava em um estado crítico.)",
		    "Tenho certeza que você deve ter um motivo bem plausível para fazer isso.",
		    "Sim, eu tenho, meus ideais.",
		    "(Ideais? Então na mente dela...... Ela realmente pensa que está fazendo algo bom...)",
		    "Então não preciso ficar irritado contigo.",
		    "Não tem?",
		    "Saiba que fiz questão de continuar atacando sem parar.",
		    "Mesmo depois dela desistir e implorar pela vida.",
		    "(Agora você comprou uma guerra bruxinha.)",
		    "(Ninguém toca nos meus amigos.)",
		    "Vai ficar só me olhando mesmo?",
		    "Não tenho tempo para perder.",
		    "Apenas saia do meu caminho.",
		    "*A bruxa invocou uma abóbora muito estranha."
		};
	public String[] itenslv4EN = {
		    "*Walking through the cave, Kayky encounters a suspicious person.",
		    "A summoning aura... So it's you.",
		    "(Surely a powerful zéfiro, much stronger than mine.)",
		    "Looking for me?",
		    "I don't remember irritating someone like you.",
		    "(You really didn't irritate me, if possible it's better to avoid another confrontation.)",
		    "(Maybe she will be willing to listen if she knows more people are involved.)",
		    "I have nothing against you, but I have orders to follow.",
		    "I get it, you want to recover what I took from that girl.",
		    "(Smart, apparently...)",
		    "Exactly, that's my mission.",
		    "I don't intend to return it.",
		    "(Maybe a bluff will help in this situation.)",
		    "Don't be stubborn, give it to me already.",
		    "Underestimating me?",
		    "Go back, if you try to chase me, you won't find anything but death.",
		    "(That didn't work, maybe a more victimized approach would be more effective...)",
		    "Stop right there, if I go back without it, I'll face something worse than death.",
		    "Consider this a warning.",
		    "(Sometimes I forget that witches are proud and narcissistic.)",
		    "(An approach like this won't gain her empathy.)",
		    "I was warned about your threat level, that's why I was chosen.",
		    "I don't fall for such a bluff, I can see the difference in our power levels.",
		    "...",
		    "(I didn't know summoners could see auras too...)",
		    "(Unlikely, maybe some other technique...)",
		    "(Damn, if I think too much, she'll suspect...)",
		    "(I can't analyze her personality yet...)",
		    "How long are you going to keep staring at me?",
		    "(I need more information to convince her...)",
		    "You said you took this from a girl, I'd like to know how you did that...",
		    ".....Ara.",
		    "Oh yes!",
		    "That fool, I had to hit her hard to make her drop this box.",
		    "(Violent, apparently.)",
		    "She was my coworker.",
		    "So I really irritated you, huh?",
		    "(I certainly don't like it when my friends get hurt.)",
		    "(The boss said she was in critical condition.)",
		    "I'm sure you must have a very good reason for doing this.",
		    "Yes, I do, my ideals.",
		    "(Ideals? So in her mind... She really thinks she's doing something good...)",
		    "Then I don't need to get angry with you.",
		    "Don't I?",
		    "Know that I made sure to keep attacking without stopping.",
		    "Even after she gave up and begged for her life.",
		    "(Now you’ve started a witch war.)",
		    "(No one touches my friends.)",
		    "Are you just going to keep staring at me?",
		    "I don’t have time to waste.",
		    "Just get out of my way.",
		    "*The witch summoned a very strange pumpkin."
		};
    public String[] itensNomelv4 = {"","Kayky","Kayky","???","???",
			"Kayky","Kayky","Kayky","???","Kayky","Kayky","???","Kayky",
			"Kayky","???","???","Kayky","Kayky","???","Kayky","Kayky",
			"Kayky","???","Kayky","Kayky","Kayky","Kayky","Kayky","???","Kayky",
			"Kayky","???","???","???","Kayky","Kayky.","???","Kayky","Kayky","Kayky","???",
			"Kayky","Kayky","???","???","???","Kayky","Kayky","???","???","???",""};
    
    public String[] itenslv5PT = {
    	    "*Kayky acabou indo para um caminho oposto ao de seu alvo.",
    	    "Por quanto tempo essa coisa vai ficar me seguindo?",
    	    "(Devia ter pego o nome dela...)",
    	    "Não vou chorar pelo leite derramado, tenho que me livrar dessa coisa.",
    	    "Vou derrotar você, e a sua mestra é a próxima!"
    	};
    public String[] itenslv5EN = {
    	    "*Kayky ended up taking a path opposite to his target.",
    	    "How long is this thing going to keep following me?",
    	    "(I should have gotten her name...)",
    	    "I’m not going to cry over spilled milk, I need to get rid of this thing.",
    	    "I will defeat you, and your master is next!"
    	};
	public String[] itensNomelv5 = {"","Kayky","Kayky","Kayky",
			"Kayky"};
	
	public String[] itenslv6PT = {
		    "*A abóbora estranha já não possui mais seu brilho.",
		    "Menos um obstáculo, agora preciso recuperar aquela ferramenta.",
		    "Aqui perto tem um castelo de vampiros...",
		    "Isso pode ser um problema...?",
		    "Que chato..."
		};
	public String[] itenslv6EN = {
		    "*The strange pumpkin no longer has its glow.",
		    "One less obstacle, now I need to recover that tool.",
		    "There's a vampire castle nearby...",
		    "This could be a problem...?",
		    "How annoying..."
		};
	public String[] itensNomelv6 = {"","Kayky","Kayky","Kayky",
		"Kayky"};
	
	//dialogos zone 4
	public String[] itenslv7PT = {
		    "*Kayky estava caminhando pelo castelo dos vampiros.",
		    "Esse é o único caminho, espero ficar longe de problemas...",
		    "*Kayky sente uma presença se aproximando.",
		    "Esse cheiro, eu já senti antes, um daqueles seres malignos.",
		    "(Uma vampira...)",
		    "(São bem parecidos com os luxares...)",
		    "(Criaturas mágicas que se alimentam da energia vital de outras...)",
		    "(Nós luxares podemos comer minérios e emoções fortes.)",
		    "(A diferença é que eles se alimentam da carne e sangue.)",
		    "Estou apenas de passagem, senhorita.",
		    "Essa é minha casa, não posso deixar qualquer um entrar.",
		    "Peço desculpas, não pretendia invadir.",
		    "Tudo bem, não estou brava.",
		    "Bem, se importa se eu continuar meu caminho?",
		    "Preciso fazer algo muito importante.",
		    "Tudo bem, aliás, me ofereço para lhe guiar.",
		    "Esse local é muito grande, pode acabar se perdendo.",
		    "Além do mais ninguém vai te atacar se estiver comigo.",
		    "(Vejo que ela é uma pessoa amigável aparentemente.)",
		    "Nossa, muito obrigado mesmo.",
		    "Por nada.",
		    "Sou Sally, e quem você seria?",
		    "Kayky, sou uma espécie de detetive.",
		    "Sério? Eu também!",
		    "Que coincidência não é mesmo.",
		    "Que tal entrar para nosso grupo? Precisamos de mais pessoas.",
		    "(Então tem mais gente do lado dela...)",
		    "Desculpe mas não posso, já tenho um time pelo qual luto.",
		    "Isso é louvável, compreendo completamente sua decisão.",
		    "Eu faria o mesmo e ficaria com meus companheiros.",
		    "De fato, você aparenta ser uma boa pessoa.",
		    "Exatamente! Vamos nos dar muito bem!",
		    "Aliás, se importa se eu perguntar algo?",
		    "Diga.",
		    "Seu cheiro é muito familiar.",
		    "Qual o nome do seu time?",
		    "(Normalmente tento manter sigilo, mas não vejo problemas em falar.)",
		    "(Ela não parece violenta e possivelmente vai me ajudar no castelo.)",
		    "(Talvez seja útil para mostrar os caminhos daqui.)",
		    "Kooba.",
		    "Kooba?",
		    "Sim, os líderes dizem que lutamos pela paz.",
		    "Poxa, isso muda tudo...",
		    "Achei que tinha feito um novo amigo, mas agora vou ter que te matar...",
		    "Me matar?",
		    "Meu grupo é Satou, somos inimigos então...",
		    "(Pensei que Satou estava bem longe ainda.)",
		    "(Parece que estão expandindo sua influência bem rapidamente.)",
		    "Entendo...",
		    "Mesmo assim não pretendo lutar.",
		    "Desculpa.",
		    "..."
		};
	public String[] itenslv7EN = {
		    "*Kayky was walking through the vampire castle.",
		    "This is the only way, I hope to stay away from trouble...",
		    "*Kayky feels a presence approaching.",
		    "That smell, I've felt it before, one of those evil beings.",
		    "(A vampire...)",
		    "(They're very similar to luxares...)",
		    "(Magical creatures that feed on the vital energy of others...)",
		    "(We luxares can eat minerals and strong emotions.)",
		    "(The difference is that they feed on flesh and blood.)",
		    "I'm just passing through, miss.",
		    "This is my home, I can't let just anyone enter.",
		    "Apologies, I didn't mean to intrude.",
		    "It's fine, I'm not mad.",
		    "Well, do you mind if I continue on my way?",
		    "I have something very important to do.",
		    "It's fine, by the way, I offer to guide you.",
		    "This place is very large, you might get lost.",
		    "Besides, no one will attack you if you're with me.",
		    "(I see she's a friendly person apparently.)",
		    "Wow, thank you so much.",
		    "You're welcome.",
		    "I'm Sally, and who would you be?",
		    "Kayky, I'm a sort of detective.",
		    "Really? Me too!",
		    "What a coincidence, isn't it?",
		    "How about joining our group? We need more people.",
		    "(So there are more people on her side...)",
		    "Sorry but I can't, I already have a team I fight for.",
		    "That's commendable, I completely understand your decision.",
		    "I would do the same and stay with my companions.",
		    "Indeed, you seem like a good person.",
		    "Exactly! We're going to get along very well!",
		    "By the way, do you mind if I ask something?",
		    "Go ahead.",
		    "Your scent is very familiar.",
		    "What's the name of your team?",
		    "(Normally I try to keep it secret, but I don't see a problem in telling.)",
		    "(She doesn't seem violent and might help me in the castle.)",
		    "(Maybe it will be useful to show the way around here.)",
		    "Kooba.",
		    "Kooba?",
		    "Yes, the leaders say we fight for peace.",
		    "Wow, that changes everything...",
		    "I thought I made a new friend, but now I'll have to kill you...",
		    "Kill me?",
		    "My group is Satou, we are enemies then...",
		    "(I thought Satou was still far away.)",
		    "(Looks like they're expanding their influence pretty quickly.)",
		    "I see...",
		    "Even so, I don't intend to fight.",
		    "Sorry.",
		    "..."
		};
	public String[] itensNomelv7 = {"","Kayky","",
			"???","Kayky","Kayky","Kayky",
			"Kayky","Kayky","Kayky",
			"???","Kayky","???","Kayky",
			"Kayky","???","???","???",
			"Kayky","Kayky","???","Sally","Kayky",
			"Sally","Sally","Sally","Kayky","Kayky",
			"Sally","Sally","Kayky",
			"Sally","Sally","Kayky","Sally","Sally","Kayky",
			"Kayky","Kayky","Kayky","Sally","Kayky",
			"Sally","Sally","Kayky","Sally","Kayky","Kayky",
			"Kayky","Kayky","Sally","Sally"};
	
	public String[] itenslv8PT = {
		    "*Kayky encontra Sally.",
		    "Sério, para veio! vamos deixar as rivalidades de nossos chefes pra lá.",
		    "Não posso, eu fiz um juramento.",
		    "Meus irmãos de batalha lutaram com todas as forças contra seu grupo.",
		    "Devo vingar aqueles que morreram pra que eu estivesse aqui.",
		    "(Então ela quer cumprir um juramento...)",
		    "(Talvez eu e Kalhiko não sejamos os únicos que carregam promessas.)",
		    "(Me pergunto se ela não está trilhando o mesmo caminho que o meu.)",
		    "(Cego por promessas acabei matando a namorada do meu melhor amigo.)",
		    "(Descobri da pior forma possível que ser frio não é ser forte.)",
		    "Não quero lutar com você Sally.",
		    "Também não quero lutar com você Kayky.",
		    "Mas não posso deixar de fazer isso, é pelos meus companheiros.",
		    "Sei que você provavelmente não tem nada a ver com isso, mas eu jurei.",
		    "Apenas finja que nunca me viu...",
		    "Não posso...",
		    "(Vejo que ela não mudará de ideia.)",
		    "Não quero mesmo lutar com você.",
		    "Desculpa..."
		};
	public String[] itenslv8EN = {
		    "*Kayky meets Sally.",
		    "Seriously, stop man! Let's leave our leaders' rivalries aside.",
		    "I can't, I made an oath.",
		    "My battle brothers fought with all their strength against your group.",
		    "I must avenge those who died so I could be here.",
		    "(So she wants to fulfill an oath...)",
		    "(Maybe me and Kalhiko aren't the only ones carrying promises.)",
		    "(I wonder if she's not walking the same path as mine.)",
		    "(Blinded by promises, I ended up killing my best friend's girlfriend.)",
		    "(I learned the hard way that being cold isn't being strong.)",
		    "I don't want to fight you, Sally.",
		    "I don't want to fight you either, Kayky.",
		    "But I can't stop doing this, it's for my comrades.",
		    "I know you probably have nothing to do with this, but I swore it.",
		    "Just pretend you never saw me...",
		    "I can't...",
		    "(I see she won't change her mind.)",
		    "I really don't want to fight you.",
		    "Sorry..."
		};
	public String[] itensNomelv8 = {"","Kayky","Sally",
			"Sally","Sally","Kayky",
			"Kayky","Kayky","Kayky",
			"Kayky","Kayky","Sally","Sally",
			"Sally","Kayky","Sally","Kayky",
			"Kayky","Sally"};
	
	public String[] itenslv9PT = {
		    "E... Eu... Perdi...",
		    "Não trai... Meus irmãos de batalha...",
		    "(Que feroz, realmente lutou até o fim para cumprir o que prometeu.)",
		    "(Eu prometi para a chefia que voltaria inteiro.)",
		    "(Sally foi até o fim... Então eu não tenho o direito de fazer menos.)",
		    "(Preciso voltar com vida, mesmo que isso acabe com a sua...)",
		    "Você foi até o final sem quebrar sua palavra.",
		    "É agora que eu morro, não é?",
		    "Que triste... Queria fazer muitas coisas ainda...",
		    "...",
		    "Sabe... Espero que haja um paraíso...",
		    "Onde possamos nos reencontrar...",
		    "Mas dessa vez... como amigos...",
		    "Cof... Cof...",
		    "Adeus Satou, Adeus castelo, adeus Ketsue...",
		    "(Ketsue?)",
		    "Cof... Eu fui feliz e acredito que tudo valeu a pena...",
		    "... Descanse em paz... Sally...",
		    "Sim...",
		    "Paz...",
		    "..."
		};
	public String[] itenslv9EN = {
		    "E... I... Lost...",
		    "I didn't betray... My battle brothers...",
		    "(How fierce, truly fought until the end to keep her word.)",
		    "(I promised the leadership I'd return in one piece.)",
		    "(Sally went all the way... So I don't have the right to do any less.)",
		    "(I need to return alive, even if it means ending yours...)",
		    "You made it to the end without breaking your word.",
		    "Is this where I die, isn't it?",
		    "How sad... I still wanted to do many things...",
		    "...",
		    "You know... I hope there's a paradise...",
		    "Where we can meet again...",
		    "But this time... as friends...",
		    "Cough... Cough...",
		    "Goodbye Satou, Goodbye castle, goodbye Ketsue...",
		    "(Ketsue?)",
		    "Cough... I was happy and I believe it was all worth it...",
		    "... Rest in peace... Sally...",
		    "Yes...",
		    "Peace...",
		    "..."
		};
	public String[] itensNomelv9 = {"Sally","Sally","Kayky",
			"Kayky","Kayky","Kayky",
			"Kayky","Sally","Sally","Sally","Sally",
			"Sally","Sally","Sally","Sally",
			"Kayky","Sally","Kayky","Sally","Sally","Sally"};
	
	//zone bia
	public String[] itenslv10PT = {
		    "*Uma pessoa muito diferente vai até a sala de Mary.",
		    "Me chamou chefe?",
		    "Você voltou mais rápido do que eu esperava.",
		    "Meus parabéns, trabalho excelente, Bia.",
		    "Vindo de ti é um grande elogio.",
		    "Vamos direto ao ponto.",
		    "Sim?",
		    "Mandei o meu subordinado Kayky em uma missão difícil.",
		    "Você consegue rastrear ele?",
		    "HA, o meu servo!",
		    "Mas é claro, posso rastrear qualquer um que já vi.",
		    "Quero que vá até ele e o ajude.",
		    "Entendido.",
		    "Seu desejo é uma ordem pra mim."
		};
	public String[] itenslv10EN = {
		    "*A very different person goes to Mary's room.",
		    "Did you call me boss?",
		    "You came back faster than I expected.",
		    "Congratulations, excellent work, Bia.",
		    "Coming from you, that's a big compliment.",
		    "Let's get straight to the point.",
		    "Yes?",
		    "I sent my subordinate Kayky on a tough mission.",
		    "Can you track him?",
		    "HA, my servant!",
		    "Of course, I can track anyone I've seen.",
		    "I want you to go to him and help him.",
		    "Understood.",
		    "Your wish is my command."
		};
	public String[] itensNomelv10 = {"","???","Mary",
			"Mary","Bia","Mary","Bia",
			"Mary","Mary","Bia","Bia",
			"Mary","Bia","Bia"};
	
	public String[] itensTextlv10Bia1PT = {
		    "Que lugar pacifico....",
		    "Pelas lendas, tinha um fantasma assustador aqui.",
		    "Um fantasma assassino que empilhava os cadáveres de quem invadia o local.",
		    "......",
		    "AI AI HA HA HA!!!",
		    "Como se isso fosse verdade hahaha!",
		    "Eu nunca acreditaria nisso!",
		    "Quem tem medo de fantasmas é mó burro!",
		    "....",
		    "..........",
		    "...........................",
		    "Não estou com medo.....",
		    "Mas só por precaução vou andar mais rápido...."
		};
	public String[] itensTextlv10Bia1EN = {
		    "What a peaceful place....",
		    "According to the legends, there was a scary ghost here.",
		    "A killer ghost who would stack the corpses of those who invaded the place.",
		    "......",
		    "AI AI HA HA HA!!!",
		    "As if that's true hahaha!",
		    "I would never believe that!",
		    "People who are afraid of ghosts are so dumb!",
		    "....",
		    "..........",
		    "...........................",
		    "I'm not scared.....",
		    "But just to be safe, I'll walk faster...."
		};
	public String[] itensTextNomelv10Bia1 = {"Bia","Bia","Bia",
			"","Bia","Bia","Bia","Bia",
			"","","","Bia","Bia"};
	public String[] itensTextArtlv10Bia1 = {"BiaN","BiaN","BiaN",
			"BiaB","BiaC","BiaC","BiaN","BiaC",
			"BiaN","BiaB","BiaM","BiaM","BiaM"};
	
	public String[] itensTextlv10Bia2PT = {
		    "Essas cavernas possuem muitos estalagmites no chão.",
		    "Que desagradável.",
		    "Inclusive elas parecem estar formando um caminho.",
		    "Será que alguma coisa as formou passando por aqui?",
		    "Espero que não seja um fantasma assustador.....",
		    "Não que eu tenha medo de fantasmas HA HA HA!!!!"
		};
	public String[] itensTextlv10Bia2EN = {
		    "These caves have a lot of stalagmites on the floor.",
		    "How unpleasant.",
		    "In fact, they seem to be forming a path.",
		    "I wonder if something created them while passing through here?",
		    "I hope it's not a scary ghost.....",
		    "Not that I'm afraid of ghosts HA HA HA!!!!"
		};
	public String[] itensTextNomelv10Bia2 = {"Bia","Bia",
			"Bia","Bia",
			"Bia","Bia"};
	public String[] itensTextArtlv10Bia2 = {"BiaC","BiaS",
			"BiaC","BiaB",
			"BiaB","BiaM"};
	
	public String[] itensTextlv10BiaQuadroPT = {
		    "*Bia encontra um quadro enorme...",
		    "Gostosa!",
		    "Arauto da Ketsue? Belo nome.",
		    "Se não me engano Ketsue é um título dado para vampiras poderosas.",
		    "Seria essa pessoa uma subordinada de uma lorde Ketsue?",
		    "Bhaaaaa que massa!"
		};
	public String[] itensTextlv10BiaQuadroEN = {
		    "*Bia finds a huge painting...",
		    "Hot!",
		    "Herald of Ketsue? Nice name.",
		    "If I'm not mistaken, Ketsue is a title given to powerful vampires.",
		    "Could this person be a subordinate of a Ketsue lord?",
		    "Bhaaaaa that's awesome!"
		};
	public String[] itensTextNomelv10BiaQuadro = {"","Bia","Bia",
			"Bia","Bia",
			"Bia"};
	
	public String[] itensTextlv10BiaLusionPT = {
		    "Esse uivo... um Lusion?",
		    "Essas criaturas são perigosas...",
		    "Não imaginava que poderia ter um dentro de um castelo de vampiros...",
		    "Afinal eles são inimigos por natureza.",
		    "Alguma coisa deve ter acontecido, os vampiros não estão aqui...",
		    "Está estranhamente vazio...",
		    "Será que foi o fantasma?",
		    "TSS! NÃO!",
		    "Fantasmas NÃO existem!!!!",
		    "Bhaaaaaa, vamos lá!"
		};
	public String[] itensTextlv10BiaLusionEN = {
		    "That howl... a Lusion?",
		    "These creatures are dangerous...",
		    "I didn't expect one inside a vampire castle...",
		    "After all, they're natural enemies.",
		    "Something must've happened, the vampires aren't here...",
		    "It's strangely empty...",
		    "Could it have been the ghost?",
		    "TSS! NO!",
		    "Ghosts DO NOT exist!!!!",
		    "Bhaaaaaa, let's go!"
		};
	public String[] itensTextNomelv10BiaLusion = {"Bia","Bia",
			"Bia","Bia",
			"Bia","Bia","Bia",
			"Bia","Bia","Bia"};
	public String[] itensTextArtlv10BiaLusion = {"BiaB","BiaB",
			"BiaN","BiaC",
			"BiaN","BiaB","BiaM",
			"BiaS","BiaB","BiaS"};
	
	public String[] itensTextlv10BiaSallyPT = {
		    "Essa gostosa parece estar morta...",
		    "Tão trágico...",
		    "Talvez se eu tivesse chegado antes você estaria viva boneca.",
		    "Isso mesmo.",
		    "Meu poder é a cura afinal.",
		    "Contemple a formidável habilidade da BIA!",
		    "CURA SUPREMA E FORMIDÁVEL DA BIA FORMIDÁVEL!",
		    "Posso literalmente utilizar meu zéfiro para curar TUDO!",
		    "Posso restaurar o estado físico de um alvo que já vi.",
		    "Com isso suas feridas físicas e mentais são anuladas!",
		    "É A CURA SUPREMA E FORMIDÁVEL DA BIA FORMIDÁVEL!!!!",
		    "TÁ OUVINDO BONECA!!!",
		    "A........ Cê tá morta né....",
		    "É... Morte eu não curo boneca...",
		    "Certeza que foi o fantasma, eu vou tomar cuidado..."
		};
	public String[] itensTextlv10BiaSallyEN = {
		    "This hottie looks dead...",
		    "So tragic...",
		    "Maybe if I had arrived earlier you'd still be alive, doll.",
		    "That's right.",
		    "My power is healing, after all.",
		    "Behold the formidable power of BIA!",
		    "THE SUPREME AND FORMIDABLE HEALING OF THE FORMIDABLE BIA!",
		    "I can literally use my Zéfiro to heal EVERYTHING!",
		    "I can restore the physical state of any target I've seen.",
		    "With that, their physical and mental wounds are erased!",
		    "IT'S THE SUPREME AND FORMIDABLE HEALING OF THE FORMIDABLE BIA!!!!",
		    "YOU HEARING ME, DOLL?!",
		    "Ah... You're dead, huh...",
		    "Yeah... I can't heal death, doll...",
		    "Pretty sure it was the ghost. I'd better be careful..."
		};
	public String[] itensTextNomelv10BiaSally = {"Bia","Bia",
			"Bia","Bia","Bia",
			"Bia","Bia",
			"Bia","Bia",
			"Bia","Bia",
			"Bia","Bia","Bia",
			"Bia"};
	public String[] itensTextArtlv10BiaSally = {"BiaS","BiaB",
			"BiaB","BiaS","BiaS",
			"BiaN","BiaC",
			"BiaN","BiaB",
			"BiaN","BiaC",
			"BiaC","BiaB","BiaS",
			"BiaM"};
	
	//zone 5
	
	public String[] itensTextZone5PT = {
		    "Existe um outro caminho."
		};
	public String[] itensTextZone5EN = {
		    "There is another way."
		};
	public String[] itensTextNomeZone5 = {"Kayky"};
	
	public String[] itensTextlv10PT = {
		    "Um viajante... o que faz em um local tão remoto?",
		    "(Humana... não... pela aura é uma bruxa.)",
		    "Só de passagem, mas pergunto o mesmo sobre você.",
		    "Eu? Apenas passeando também.",
		    "IH nem me apresentei!",
		    "Prazer, Lili Sibila! Sou uma bruxa, então pode me encher de elogios.",
		    "Sou Kayky Imir, é um prazer te conhecer.",
		    "Bem, posso te perguntar uma coisa?",
		    "Mas é claro.",
		    "É sobre outra bruxa, preciso encontrar ela.",
		    "Conheço quase todas, só preciso saber o nome.",
		    "Desculpe, mas não sei o nome dela.",
		    "Assim é mais difícil, posso tentar descobrir pela aparência.",
		    "Vejamos, ela era loira e possuía olhos amarelos.",
		    "Bhaaaa. Nada, preciso de mais informações.",
		    "(Conheço esse \"bhaaa\")",
		    "Se não me engano...",
		    "Um metro e sessenta e dois, físico mediano, olhos dourados.",
		    "Roupa cinza com detalhes azuis, voz forte, mentalidade sádica.",
		    "Bruxa invocadora, cabelo longo e destra aparentemente.",
		    "Invocadora? AH! Muriel!",
		    "Não existem muitas invocadoras loiras, conheço todas HA HA HA!!!",
		    "Ela é bem famosa entre as bruxas.",
		    "Dizem que ela enfrentou uma Lorde vampira de Satou.",
		    "Essa foi fácil!",
		    "Mas pera ae... Tu é um psicopata observador?",
		    "(Então ela não é da organização Satou...)",
		    "(Aparentemente ela espancou minha parceira apenas por prazer mesmo...)",
		    "Bem, e onde ela fica?",
		    "Não vou contar.",
		    "Por que?",
		    "Nem te conheço, vamos me elogie!",
		    "HA HA! Foi mal, desculpa se isso te incomodou!",
		    "(Esqueci que as bruxas são orgulhosas.)",
		    "Você é uma beldade, acima da média, inteligente, gente boa, sabia e incrível.",
		    "Exatamente!",
		    "(Fácil de se ler como um livro aberto.........)",
		    "Pode me dizer onde ela fica agora?",
		    "Mas é claro!",
		    "Se liga meu mano que eu vou te passar a visão.",
		    "Ela fica num pântano perto de um vulcão.",
		    "Uns 90 quilômetros para o Leste.",
		    "Continue em linha reta ali na frente.",
		    "Nossa, muito obrigado mesmo.",
		    "Pois é, eu sou incrível não é mesmo?",
		    "Sim, muito incrível.",
		    "EXATAMENTE HA HA HA!!!"
		};
	public String[] itensTextlv10EN = {
		    "A traveler... what are you doing in such a remote place?",
		    "(Human... no... judging by the aura, a witch.)",
		    "Just passing by, but I ask the same about you.",
		    "Me? Just wandering around too.",
		    "OH I didn't even introduce myself!",
		    "Nice to meet you, Lili Sibila! I'm a witch, so shower me with compliments.",
		    "I'm Kayky Imir, pleasure to meet you.",
		    "Well, can I ask you something?",
		    "But of course.",
		    "It's about another witch, I need to find her.",
		    "I know almost all of them, just need a name.",
		    "Sorry, I don't know her name.",
		    "That makes it harder, but I can try by appearance.",
		    "Let's see... she was blonde and had yellow eyes.",
		    "Bhaaaa. Nothing yet, I need more info.",
		    "(I know that 'bhaaa'...)",
		    "If I’m not mistaken...",
		    "About five foot four, average build, golden eyes.",
		    "Gray clothes with blue details, strong voice, sadistic mind.",
		    "Summoner witch, long hair, right-handed apparently.",
		    "Summoner? AH! Muriel!",
		    "There aren't many blonde summoners, I know them all HA HA HA!!!",
		    "She's quite famous among witches.",
		    "They say she fought a Vampire Lord from Satou.",
		    "That one was easy!",
		    "But wait... are you some kind of psycho observer?",
		    "(So she's not from the Satou organization...)",
		    "(Apparently she beat up my partner just for fun...)",
		    "Well, and where is she?",
		    "I’m not telling you.",
		    "Why not?",
		    "I don’t even know you. Come on, compliment me!",
		    "HA HA! Sorry, didn’t mean to offend you!",
		    "(I forgot witches are proud.)",
		    "You're a beauty, above average, intelligent, kind, wise and amazing.",
		    "Exactly!",
		    "(So easy to read like an open book.........)",
		    "Can you tell me where she is now?",
		    "But of course!",
		    "Check this out, my dude.",
		    "She’s in a swamp near a volcano.",
		    "About 90 kilometers to the east.",
		    "Keep going straight ahead from here.",
		    "Wow, thanks a lot.",
		    "Yup, I’m amazing, aren’t I?",
		    "Yes, very amazing.",
		    "EXACTLY HA HA HA!!!"
		};

	public String[] itensTextNomelv10 = {"???","Kayky","Kayky",
			"???","???","Lili",
			"Kayky","Kayky","Lili",
			"Kayky","Lili","Kayky",
			"Lili","Kauky",
			"Lili","Kayky","Kayky",
			"Kayky","Kayky",
			"Kayky","Lili","Lili",
			"Lili","Lili","Lili",
			"Lili","Kayky","Kayky",
			"Kayky","Lili","Kayky","Lili","Lili",
			"Kayky","Kayky",
			"Lili","Kayky","Kayky","Lili",
			"Lili","Lili","Lili",
			"Lili","Kayky","Lili",
			"Kayky","Lili"};
	public String[] itensTextArtlv10 = {"LiliN","KaykyN","KaykyN",
			"LiliN","LiliC","LiliC",
			"KaykyN","KaykyN","LiliC",
			"KaykyN","LiliN","KaykyC",
			"LiliN","KaykyN",
			"LiliC","KaykyC","KaykyN",
			"KaykyN","KaykyN",
			"KaykyN","LiliC","LiliC",
			"LiliN","LiliN","LiliN",
			"LiliC","KaykyN","KaykyN",
			"KaykyN","LiliB","KaykyC","LiliC","LiliN",
			"KaykyC","KaykyN",
			"LiliC","KaykyC","KaykyN","LiliC",
			"LiliC","LiliN","LiliN",
			"LiliN","KaykyN","LiliN",
			"KaykyN","LiliB"};
	
	public String[] itensTextlv10DPT = {
		    "CONFIA!!!!",
		    "(Super suspeita...)"
		};
	public String[] itensTextlv10DEN = {
		    "TRUST ME!!!!",
		    "(Super suspicious...)"
		};
	public String[] itensTextNomelv10D = {"Lili","Kayky"};
	public String[] itensTextArtlv10D = {"LiliB","KaykyC"};
	

	public String[] itensTextlv11PT = {
		    "Você ai!",
		    "Você outra vez?",
		    "Parece que escapou da minha abobora.",
		    "Eu destrui ela, e se não me devolver a ferramenta você será a próxima!",
		    "Está falando muito, não tenho tempo para perder.",
		    "Vamos acabar com isso, tenho que salvar o mundo.",
		    "(Que?)"
		};
	public String[] itensTextlv11EN = {
		    "You there!",
		    "You again?",
		    "Looks like you escaped from my pumpkin.",
		    "I destroyed it, and if you don't give the tool back, you'll be next!",
		    "You're talking too much, I don't have time to waste.",
		    "Let's end this, I have to save the world.",
		    "(What?)"
		};
	public String[] itensTextNomelv11 = {"Kayky","Muriel","Muriel","Kayky","Muriel",
			"Muriel","Kayky"};
	public String[] itensTextArtlv11 = {"KaykyB","MurielB","MurielN","KaykyB","MurielN",
			"MurielB","KaykyC"};
	
	public String[] itensTextlv12PT = {
		    "Que perda de tempo, é nítida a diferença de nosso poder.",
		    "Você não tem chance nenhuma contra mim.",
		    "(O zéfiro dela emite uma aura poderosa.)",
		    "(Parece que não consegui ver a diferença de nosso nível de poder...)",
		    "(Será que vou morrer assim como Dujhin?)",
		    "(Não... eu prometi...)",
		    "...",
		    "Agora apenas morra de uma vez.",
		    "...",
		    "(Vou ter que usar aquilo, ou vou morrer...)",
		    "Um segundo!"
		};
	public String[] itensTextlv12EN = {
		    "What a waste of time. The gap in our power is obvious.",
		    "You don’t stand a chance against me.",
		    "(Her Zephyr emits a powerful aura.)",
		    "(I couldn’t grasp the difference in our power levels...)",
		    "(Am I going to die... just like Dujhin?)",
		    "(No... I made a promise...)",
		    "...",
		    "Now just die already.",
		    "...",
		    "(I’ll have to use *that*, or I’ll die...)",
		    "One second!"
		};
	public String[] itensTextNomelv12 = {"Muriel","Muriel",
			"Kayky","Kayky",
			"Kayky","Kayky","Kayky","Muriel",
			"Kayky","Kayky","???"};
	public String[] itensTextArtlv12 = {"MurielB","MurielB",
			"KaykyB","KaykyC",
			"KaykyN","KaykyN","KaykyC","MurielS",
			"KaykyB","KaykyB",""};
	
	public String[] itenslv12PT = {
		    "Vim avisar que eu sou o amor da sua vida!",
		    "E você não pode matar meu servo!",
		    "...",
		    "Servo? Prefiro morrer mesmo Bia.",
		    "(Fico muito feliz em te ver companheira.)",
		    "Quanto tempo Kayky!",
		    "O que faz aqui? Chegou numa boa hora.",
		    "A chefe me disse para vir.",
		    "Entendo...",
		    "E quem é essa meliante aí?",
		    "...",
		    "Quanta conversa fiada.",
		    "Eu sou Muriel, a bruxa invocadora.",
		    "Eu vou absorver essa sua ferramenta, e invocar um deus.",
		    "Vou trazer a paz para o nosso mundo, mesmo que seja através da força.",
		    "Vou acabar com a guerra de Satou.",
		    "(Satou queria invocar uma entidade...)",
		    "(Será que ela pode mesmo invocar um deus?)",
		    "Que narcisista.",
		    "As bruxas são orgulhosas lembra...",
		    "Orgulhosa?",
		    "Não ligo para tais comentários de pessoas esquisitas.",
		    "Bhaaa.",
		    "Quero ver falar isso quando eu arrancar todos os seus dentes.",
		    "Apenas pedras no meu caminho."
		};
	public String[] itenslv12EN = {
		    "I came to tell you that I am the love of your life!",
		    "And you can’t kill my servant!",
		    "...",
		    "Servant? I’d rather die than serve Bia.",
		    "(I’m so happy to see you, my companion.)",
		    "It’s been a while, Kayky!",
		    "What brings you here? You arrived just in time.",
		    "The boss told me to come.",
		    "I see...",
		    "And who is this miscreant here?",
		    "...",
		    "So much nonsense.",
		    "I’m Muriel, the summoner witch.",
		    "I’m going to absorb your tool and summon a god.",
		    "I’ll bring peace to our world, even if it’s through force.",
		    "I’ll end the war of Satou.",
		    "(Satou wanted to summon an entity...)",
		    "(Can she really summon a god?)",
		    "What a narcissist.",
		    "Witches are proud, remember...",
		    "Proud?",
		    "I don’t care about comments from strange people.",
		    "Bhaaa.",
		    "Let’s see you say that when I pull out all your teeth.",
		    "Just rocks in my way."
		};
	public String[] itensNomelv12 = {"Bia","Bia","Muriel",
			"Kayky","Kayky","Bia",
			"Kayky","Bia","Kayky","Bia",
			"Muriel","Muriel","Muriel","Muriel",
			"Muriel","Muriel",
			"Kayky","Kayky","Bia","Kayky",
			"Muriel","Muriel","Bia","Bia",
			"Muriel"};
	
	public String[] itensTextlv12DPT = {
		    "Não me atrasem, enquanto conversamos milhares de pessoas morrem na guerra.",
		    "Apenas morram seus tolos.",
		    "MORRAM!."
		};
	public String[] itensTextlv12DEN = {
		    "Don’t delay me, while we talk, thousands of people are dying in the war.",
		    "Just die, you fools.",
		    "DIE!"
		};
	public String[] itensTextNomelv12D = {"Muriel","Muriel","Muriel"};
	public String[] itensTextArtlv12D = {"MurielC","MurielC","MurielB"};
	
	
	//zone 6
	
	public String[] itensTextlv13PT = {
		    "Chega!",
		    "Tolos!",
		    "Vamos acabar com isso agora!",
		    "Certamente iremos!"
		};
	public String[] itensTextlv13EN = {
		    "Enough!",
		    "Fools!",
		    "Let’s end this now!",
		    "We certainly will!"
		};
	public String[] itensTextNomelv13 = {"Muriel","Muriel","Muriel","Kayky"};
	public String[] itensTextArtlv13 = {"MurielB","MurielC","MurielB","KaykyB"};
	
	public String[] itensTextlv13BiaPT = {
		    "Hey boneca, sabe qual é o melhor amigo do ser humano?",
		    "Não interessa!",
		    "O cachorro! Aquele que aperta seu pescoço!!!"
		};
	public String[] itensTextlv13BiaEN = {
		    "Hey doll, do you know what’s a human’s best friend?",
		    "Doesn't matter!",
		    "How annoying! I'm going to squeeze your neck!!!"
		};
	public String[] itensTextNomelv13Bia = {"Bia","Muriel","Bia"};
	public String[] itensTextArtlv13Bia = {"BiaC","MurielB","BiaC"};
	
	
	public String[] itenslv13PT = {
		    "HA HA HA HA HA!!!!!!!!",
		    "Então é essa pessoa que vai salvar o mundo???????",
		    "HAAAAAA HA! TÁ BOM!",
		    "Saiam do meu caminho.",
		    "Tolos!",
		    "Saiam. Saiam.",
		    "SAIAM!",
		    "SAIAM SAIAM SAIAM SAIAM SAIAM SAIAM SAIAM SAIAM SAIAM!",
		    "TOLOS! VOCÊS NÃO ENTENDEM?",
		    "Roubos, guerras e extermínios! Tudo que Satou está fazendo!",
		    "O mundo está MORRENDO! Alguém precisa manter a ordem!",
		    "...",
		    "Me escutem, se eu absorver essa coisa posso invocar um deus.",
		    "Com ele podemos acabar com todos os confrontos do mundo.",
		    "Ninguém vai se arriscar a contrariar uma divindade, as guerras vão acabar.",
		    "(Sádica e explosiva… Possivelmente ela vai criar uma ditadura.)",
		    "Que idiota, acha mesmo que um deus vai dar ouvidos para uma bruxa?",
		    "Não vejo isso acontecendo.",
		    "Mas existe a possibilidade.",
		    "Vamos ter um mundo onde todos vão viver felizes.",
		    "Terei poder para obliterar qualquer oponente!",
		    "Farei questão de manter a ordem no mundo!",
		    "Inimigos se tornarão amigos, as pessoas não perderão tudo em guerras!",
		    "Não haverá lutas, todos irão se amar, mesmo que seja através da força.",
		    "Bhaaa!",
		    "Da forma como você disse, isso não é bom.",
		    "Bia está certa, viver sob as ordens de alguém não vai trazer felicidade.",
		    "Seremos apenas pássaros em uma gaiola.",
		    "Vocês não entendem mesmo...",
		    "TOLOS!",
		    "*A bruxa consegue escapar."
		};
	public String[] itenslv13EN = {
		    "HA HA HA HA HA!!!!!!!!",
		    "So this is the person who’s going to save the world???????",
		    "HAAAAAA HA! OK!",
		    "Get out of my way.",
		    "Fools!",
		    "Get out. Get out.",
		    "GET OUT!",
		    "GET OUT GET OUT GET OUT GET OUT GET OUT GET OUT GET OUT GET OUT!",
		    "FOOLS! YOU DON’T UNDERSTAND?",
		    "Robberies, wars, and exterminations! Everything Satou is doing!",
		    "The world is DYING! Someone needs to keep order!",
		    "...",
		    "Listen, if I absorb this thing I can summon a god.",
		    "With him, we can end all the world’s conflicts.",
		    "No one will dare go against a deity, the wars will end.",
		    "(Sadistic and explosive… She’s probably going to create a dictatorship.)",
		    "What an idiot, you really think a god will listen to a witch?",
		    "I don’t see that happening.",
		    "But there is a possibility.",
		    "We will have a world where everyone will live happily.",
		    "I will have the power to obliterate any opponent!",
		    "I will make sure to keep order in the world!",
		    "Enemies will become friends, people won’t lose everything in wars!",
		    "There won’t be fights, everyone will love each other, even if it’s through force.",
		    "Bhaaa!",
		    "The way you said it, that’s not good.",
		    "Bia is right, living under someone’s orders won’t bring happiness.",
		    "We will only be birds in a cage.",
		    "You really don’t understand...",
		    "FOOLS!",
		    "*The witch manages to escape."
		};
	public String[] itensNomelv13 = {"Bia","Bia","Bia",
			"Muriel","Muriel","Muriel","Muriel","Muriel","Muriel",
			"Muriel","Muriel",
			"Muriel","Muriel","Muriel",
			"Muriel","Kayky",
			"Bia","Kayky","Muriel",
			"Muriel","Muriel","Muriel",
			"Muriel","Muriel",
			"Bia","Bia","Kayky",
			"Kayky","Muriel","Muriel",""};
	
	public String[] itenslv13DPT = {
		    "Isso não vai ficar assim, nos veremos novamente.",
		    "Vou te fazer passar pelo inferno, verdinho.",
		    "(Vejo que ela está no limite, caso contrário não teria aberto essa distância.)",
		    "(Eu também mal consigo me mexer, melhor terminar por aqui.)",
		    "(Afinal peguei a caixa enquanto a Bia estrangulava ela.)",
		    "Perdemos ela Bia...",
		    "Mas ela está logo ali",
		    "Não consigo subir nas árvores com tanta facilidade.",
		    "Bhaaa, então....",
		    "Vamos voltar, agora que vi ela posso rastrear a qualquer momento.",
		    "Continuar lutando só vai me deixar cansada mesmo.",
		    "Você está certa.",
		    "Vamos apenas devolver isso para a nossa chefe.",
		    "Um deus... O que ela deve estar pensando?...",
		    "Não é da sua conta!",
		    "(Mas ela já disse o que pretendia fazer...)",
		    "Bia...",
		    "Uhm?",
		    "Obrigado mesmo, achei que ia morrer.",
		    "Não agradeça, me dê chocolates.",
		    "Afinal eu, corajosamente, enfrentei um fantasma poderoso no caminho para poder te achar.",
		    "(Parece que ela também enfrentou o Dujhin. Mas como?)",
		    "Sim... Vou fazer isso...",
		    "Vou ter minha vingança. Até mais.",
		    "*A bruxa desapareceu nas sombras da árvore.",
		    "Vamos Bia",
		    "*Kayky e Bia voltam vitoriosos para sua organização."
		};
	public String[] itenslv13DEN = {
		    "This won’t be the end, we’ll meet again.",
		    "I’m going to make you go through hell, greeny.",
		    "(I can see she’s at her limit, otherwise, she wouldn’t have opened that distance.)",
		    "(I can barely move, better end it here.)",
		    "(After all, I grabbed the box while Bia was strangling her.)",
		    "We lost her Bia...",
		    "But she’s right over there.",
		    "I can’t climb trees that easily.",
		    "Bhaaa, so....",
		    "Let’s go back, now that I saw her I can track her anytime.",
		    "Continuing to fight will only wear me out.",
		    "You’re right.",
		    "Let’s just return this to our boss.",
		    "A god... What could she be thinking?...",
		    "That’s none of your business!",
		    "(But she already said what she intended to do...)",
		    "Bia...",
		    "Uhm?",
		    "Thanks a lot, I thought I was going to die.",
		    "Don’t thank me, give me chocolates.",
		    "After all, I bravely faced a powerful ghost on the way to find you.",
		    "(It seems like she also faced Dujhin. But how?)",
		    "Yes... I’ll do that...",
		    "I’ll have my revenge. See you later.",
		    "*The witch disappears into the shadows of the tree.",
		    "Let’s go Bia.",
		    "*Kayky and Bia return victorious to their organization."
		};
	public String[] itensNomelv13D = {"Muriel","Muriel","Kayky",
			"Kayky","Kayky",
			"Kayky","Bia","Kayky","Bia",
			"Bia","Bia",
			"Kayky","Kayky","Bia",
			"Muriel","Kayky","Kayky","Bia","Kayky",
			"Bia","Bia",
			"Kayky","Kayky","Muriel",
			"","Kayky",""};
	
	
	// zone 7
	
	public String[] itensTextlv14PT = {
		    "Seja bem-vindo novamente, Kayky.",
		    "A chefia está te esperando."
		};
	public String[] itensTextlv14EN = {
		    "Welcome back, Kayky.",
		    "The boss is waiting for you."
		};
	public String[] itensTextNomelv14 = {"Druida","Druida"};
	
	public String[] itenslv14PT = {
		    "Chefe.",
		    "Recuperaram a ferramenta?",
		    "(Nenhuma reação ao me ver vivo...)",
		    "Sim...",
		    "Abriram essa caixa?",
		    "Não...",
		    "Muito bem.",
		    "(...)",
		    "(Bem, existem muitos funcionários afinal...)",
		    "(Minha parceira está em um estado crítico...)",
		    "(Kalhiko foi para uma missão difícil, possivelmente...)",
		    "(Bia está exausta...)",
		    "(Ela deve estar mais preocupada com eles...)",
		    "...",
		    "Kayky?",
		    "...",
		    "(De qualquer forma...)",
		    "(Devo me esforçar por eles também.)",
		    "Chefe... O que tem nessa caixa?",
		    "A bruxa disse que pode invocar um deus com isso...",
		    "Bem...",
		    "Meu caríssimo subordinado... É melhor não saber.",
		    "É pelo seu bem. Okay?",
		    "Entendido.",
		    "Agora vá chamar a Bia, preciso falar com ela também.",
		    "Verdade, ela pode rastrear a bruxa agora.",
		    "Exatamente meu caro.",
		    "Entendido, estou indo chefia.",
		    "Kayky!",
		    "Sim?",
		    "Fico feliz em te ver vivo.",
		    "(....)",
		    "(Essa é Mary, a minha chefia, uma das líderes da nossa organização.)",
		    "(Apesar da atmosfera tensa que a chefia causa...)",
		    "(Parece que realmente se importa com seus subordinados...)",
		    "(Sim, por essas pessoas que eu luto.)",
		    "Morrer não era uma opção.",
		    "Até mais chefe.",
		    "Até Kayky."
		};
	public String[] itenslv14EN = {
		    "Boss.",
		    "Did you recover the tool?",
		    "(No reaction to seeing me alive...)",
		    "Yes...",
		    "Did you open this box?",
		    "No...",
		    "Very well.",
		    "(...)",
		    "(Well, there are many employees after all...)",
		    "(My partner is in a critical state...)",
		    "(Kalhiko went on a difficult mission, probably...)",
		    "(Bia is exhausted...)",
		    "(She must be more worried about them...)",
		    "...",
		    "Kayky?",
		    "...",
		    "(Anyway...)",
		    "(I must strive for them too.)",
		    "Boss... What's in this box?",
		    "The witch said she can summon a god with it...",
		    "Well...",
		    "My dear subordinate... It's better not to know.",
		    "It's for your own good. Okay?",
		    "Understood.",
		    "Now go call Bia, I need to talk to her too.",
		    "True, she can track the witch now.",
		    "Exactly, my dear.",
		    "Understood, I'm going boss.",
		    "Kayky!",
		    "Yes?",
		    "I'm glad to see you alive.",
		    "(....)",
		    "(This is Mary, my boss, one of the leaders of our organization.)",
		    "(Despite the tense atmosphere my boss causes...)",
		    "(It seems she really cares about her subordinates...)",
		    "(Yes, I fight for these people.)",
		    "Dying was not an option.",
		    "See you later boss.",
		    "See you, Kayky."
		};
	public String[] itensNomelv14 = {"Kayky","Mary","Kayky","Kayky",
			"Mary","Kayky","Mary","Kayky","Kayky",
			"Kayky","Kayky",
			"Kayky","Kayky","Kayky","Mary","Kayky","Kayky",
			"Kayky","Kayky","Kayky",
			"Mary","Mary","Mary","Kayky",
			"Mary","Kayky",
			"Mary","Kayky","Mary","Kayky","Kayky","Kayky",
			"Kayky","Kayky",
			"Kayky","Kayky",
			"Kayky","Kayky","Mary"};
	
	public String[] itenslv15PT = {
		    "Que chato, o jogo acabou e eu nem apareci.",
		    "Bem, antes tarde do que nunca.",
		    "Olá humano."
		};
	public String[] itenslv15EN = {
		    "How boring, the game is over and I didn't even appear.",
		    "Well, better late than never.",
		    "Hello human."
		};
	public String[] itensNomelv15 = {"???","???","???"};
	
	
	
	public static boolean showMessage = false;
	public boolean show = false;
	private int index = 0, maxIndex = itensPT.length, maxIndexText = itensTextPT.length;
	private int maxIndexTextKalhiko = kalhikoPT.length;
	private int maxIndexTextKalhiko2 = kalhiko2PT.length;
	private int maxIndexTextKalhiko3 = kalhiko3PT.length;
	private int maxIndexTextlv2 = itensTextlv2PT.length;
	private int maxIndexTextlv10 = itensTextlv10PT.length;
	private int maxIndexTextlv10Bia1 = itensTextlv10Bia1PT.length;
	private int maxIndexTextlv10Bia2 = itensTextlv10Bia2PT.length;
	private int maxIndexTextlv10BiaQuadro = itensTextlv10BiaQuadroPT.length;
	private int maxIndexTextlv10BiaLusion = itensTextlv10BiaLusionPT.length;
	private int maxIndexTextlv10BiaSally = itensTextlv10BiaSallyPT.length;
	private int maxIndexTextZone5 = itensTextZone5PT.length;
	private int maxIndexTextlv10D = itensTextlv10DPT.length;
	private int maxIndexTextlv11 = itensTextlv11PT.length;
	private int maxIndexTextlv12 = itensTextlv12PT.length;
	private int maxIndexTextlv12D = itensTextlv12DPT.length;
	private int maxIndexTextlv13 = itensTextlv13PT.length;
	private int maxIndexTextlv13Bia = itensTextlv13BiaPT.length;
	private int maxIndexTextlv14 = itensTextlv14PT.length;
	
	private int maxIndexlv1 = itensZone1PT.length;
	private int maxIndexlv3 = itenslv3PT.length;
	private int maxIndexlv3death = itenslv3deathPT.length;
	private int maxIndexlv4 = itenslv4PT.length;
	private int maxIndexlv5 = itenslv5PT.length;
	private int maxIndexlv6 = itenslv6PT.length;
	private int maxIndexlv7 = itenslv7PT.length;
	private int maxIndexlv8 = itenslv8PT.length;
	private int maxIndexlv9 = itenslv9PT.length;
	private int maxIndexlv10 = itenslv10PT.length;
	private int maxIndexlv12 = itenslv12PT.length;
	private int maxIndexlv13 = itenslv13PT.length;
	private int maxIndexlv13D = itenslv13DPT.length;
	private int maxIndexlv14 = itenslv14PT.length;
	private int maxIndexlv15 = itenslv15PT.length;
	
	
	
	public static boolean curIndex = false;
	public static int curIndexMsg = 0;
	public int time = 0;
	public int maxTime = 2;
	
	public void finalAnimation() {
		if(artAnimationEnd <= artAnimationEndFinal) {
			if(Game.gameState == "TEXT") {
			xArte += 16;
			}
			if(xArte >= artAnimationEndFinal) {
				xArte = artAnimationEndFinal;
				curArte = false;
				arteView = false;
				xArte = 0;
			}
		}
	}
	
	public void ativarArte() {
		curArte = true;
		arteView = true;
	}
	
	public static void ativarDialogo(){
			/*if(ativarText == 1) {
				Text.curText = 2;
			}*/
			Game.gameState = "TEXT";
		    Text.frases = true;
		    Text.showMessage = true;
		    Sound.diminuirTheme();
	}	
	public void tick() {
		
		//animação
		
		if(artAnimation <= artAnimationFinal && artAnimationEndState == false && arteView == true) {
			xArte += 16;
			if(xArte >= artAnimationFinal) {
				xArte = artAnimationFinal;
			}
		}
		finalAnimation();
		
		// frases e artes
		
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 0 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndex){
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							Sound.aumentarTema();
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 2;
							xArte = 0;
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndex){
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							Sound.aumentarTema();
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 2;
							xArte = 0;
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 0 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexText){
							Npc1.checkDialogo = 0;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.aumentarTema();
							Game.gameState = "NORMAL";
						}
						
					}
			} else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexText){
							Npc1.checkDialogo = 0;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.aumentarTema();
							Game.gameState = "NORMAL";
						}
						
					}
			}
			
		}
		}
		
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 1 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			
			if(Game.Idioma == "PT") {
				if(curIndexMsg < kalhikoPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextKalhiko){
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 4;
							Sound.aumentarTema();
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < kalhikoEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextKalhiko){
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 4;
							Sound.aumentarTema();
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 4 && Game.CUR_LEVEL == 1 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < kalhiko2PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextKalhiko2){
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 6;
							Sound.aumentarTema();
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < kalhiko2EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextKalhiko2){
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 6;
							Sound.aumentarTema();
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 6 && Game.CUR_LEVEL == 1 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < kalhiko3PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextKalhiko3){
							
							Game.CUR_LEVEL = 0;
							Game.setZone = 2;
							Tranzi.setTranzi = 2;
							Tranzi.StartArt();
							Game.gameState = "TRANZI";
							
							//Game.nextLevelReset();
							Game.spritesheetArtes = new Spritesheet("/art/"+20+".png");
							Killer.killerCheck = true;
							//String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
							//World.restartGame(newWorld);
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.endTheme();
							//Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < kalhiko3EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextKalhiko3){
							
							Game.CUR_LEVEL = 0;
							Game.setZone = 2;
							Tranzi.setTranzi = 2;
							Tranzi.StartArt();
							Game.gameState = "TRANZI";
							
							//Game.nextLevelReset();
							Game.spritesheetArtes = new Spritesheet("/art/"+20+".png");
							Killer.killerCheck = true;
							//String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
							//World.restartGame(newWorld);
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.endTheme();
							//Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		//zone 2
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 0 && Game.setZone == 2) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensZone1PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv1){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Killer.killerCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensZone1EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv1){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Killer.killerCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 1 && Game.setZone == 2) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv2PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv2){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Killer.killerCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv2EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv2){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Killer.killerCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 2 && Game.setZone == 2) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv3PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv3){
								
							xArte = 0;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Killer.killerCheck = false;
							Sound.aumentarTema = true;
							Game.spritesheetArtes = new Spritesheet("/art/"+22+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							Game.gameState = "NORMAL";
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv3EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv3){
								
							xArte = 0;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Killer.killerCheck = false;
							Sound.aumentarTema = true;
							Game.spritesheetArtes = new Spritesheet("/art/"+22+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							Game.gameState = "NORMAL";
							
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 3 && Game.CUR_LEVEL == 2 && Game.setZone == 2) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv3deathPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							/*if(index == (maxIndexlv3death - 5)){
								Sound.endTema = true;
								Sound.endTheme();
							}*/
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv3death){
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Sound.endTema = true;
							Sound.endTheme();
							//Sound.startTema = true;
							//Sound.startTheme();

							artAnimationEndState = false;
							Killer.killerCheck = false;
							Player.proximoLevelBoos = false;
							Player.proximoLevel = false;
							Killer.killerBatle = false;
							
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv3deathEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							/*if(index == (maxIndexlv3death - 5)){
								Sound.endTema = true;
								Sound.endTheme();
							}*/
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv3death){
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Sound.endTema = true;
							Sound.endTheme();
							//Sound.startTema = true;
							//Sound.startTheme();

							artAnimationEndState = false;
							Killer.killerCheck = false;
							Player.proximoLevelBoos = false;
							Player.proximoLevel = false;
							Killer.killerBatle = false;
							
							
						}
						
					}
			}
			}
			
		}
		//zone 3
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 0 && Game.setZone == 3) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv4PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv4){
							Game.spritesheetArtes = new Spritesheet("/art/"+31+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							Game.CUR_LEVEL++;
							Player.proximoLevel = false;
							Player.proximoLevelBoos = false;
							String newWorld = "level"+Game.CUR_LEVEL+".png";
							World.restartGame(newWorld);
							Sound.aumentarTema = true;

							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv4EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv4){
							Game.spritesheetArtes = new Spritesheet("/art/"+31+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							Game.CUR_LEVEL++;
							Player.proximoLevel = false;
							Player.proximoLevelBoos = false;
							String newWorld = "level"+Game.CUR_LEVEL+".png";
							World.restartGame(newWorld);
							Sound.aumentarTema = true;

							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}
			}
			
		}
		
		
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 1 && Game.setZone == 3) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv5PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv5){
							Game.spritesheetArtes = new Spritesheet("/art/"+32+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							Game.CUR_LEVEL++;
							Game.nextLevelReset();
							Player.proximoLevel = false;
							Player.proximoLevelBoos = false;
							String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
							World.restartGame(newWorld);
							
							xArte = 0;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv5EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv5){
							Game.spritesheetArtes = new Spritesheet("/art/"+32+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							Game.CUR_LEVEL++;
							Game.nextLevelReset();
							Player.proximoLevel = false;
							Player.proximoLevelBoos = false;
							String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
							World.restartGame(newWorld);
							
							xArte = 0;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 3 && Game.CUR_LEVEL == 2 && Game.setZone == 3) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv6PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv6){
							Sound.sadTheme = 1;
							Sound.endTema = true;
							Sound.endTheme();
							
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							
							xArte = 0;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv6EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv6){
							Sound.sadTheme = 1;
							Sound.endTema = true;
							Sound.endTheme();
							
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							
							xArte = 0;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							
							
						}
						
					}
			}
			}
			
		}
		//Zone 4
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 0 && Game.setZone == 4) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv7PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == 1) {
							curArte = false;
							arteView = false;
							xArte = 0;
						}
						if(index == maxIndexlv7){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Sally.SallyCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv7EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == 1) {
							curArte = false;
							arteView = false;
							xArte = 0;
						}
						if(index == maxIndexlv7){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Sally.SallyCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 2 && Game.setZone == 4) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv8PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv8){
							
							Sally.SallyCheck = false;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv8EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv8){
							
							Sally.SallyCheck = false;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
							
						}
						
					}
			}
			}
			
		}
		if(frases == true && curText == 3 && Game.CUR_LEVEL == 2 && Game.setZone == 4) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv9PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv9){
							Sound.sadTheme = 1;
							Sound.endTheme();
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Sally.SallyCheck = false;
							artAnimationEndState = false;
							Player.proximoLevel = false;
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv9EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv9){
							Sound.sadTheme = 1;
							Sound.endTheme();
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Sally.SallyCheck = false;
							artAnimationEndState = false;
							Player.proximoLevel = false;
							
						}
						
					}
			}
			}
			
		}
		
		/// zone bia
		
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 2 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv10PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv10){
							
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							Player.proximoLevel = false;
							Player.proximoLevelBoos = false;
							
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv10EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv10){
							
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							Player.proximoLevel = false;
							Player.proximoLevelBoos = false;
							
							
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 3 && Game.setZone == 2) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv10Bia1PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10Bia1){
							Tranzi.setTranzi = 1;
							Tranzi.StartArt();
							Sound.endTheme();
							Game.gameState = "TRANZI";
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv10Bia1EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10Bia1){
							Tranzi.setTranzi = 1;
							Tranzi.StartArt();
							Sound.endTheme();
							Game.gameState = "TRANZI";
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 3 && Game.setZone == 3) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv10Bia2PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10Bia2){
							Tranzi.setTranzi = 1;
							Tranzi.StartArt();
							Sound.endTheme();
							Game.gameState = "TRANZI";
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv10Bia2EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10Bia2){
							Tranzi.setTranzi = 1;
							Tranzi.StartArt();
							Sound.endTheme();
							Game.gameState = "TRANZI";
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 1 && Game.CUR_LEVEL == 3 && Game.setZone == 4) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv10BiaQuadroPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10BiaQuadro){
							
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Game.gameState = "NORMAL";
							
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv10BiaQuadroEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10BiaQuadro){
							
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Game.gameState = "NORMAL";
							
							
						}
						
					}
			}
			}
			
		}
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 3 && Game.setZone == 4) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv10BiaLusionPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10BiaLusion){
							
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							Game.gameState = "NORMAL";
							
							Quadro.check = 1;
							Botão.apertarBotão();
							Lusion.spawnLusion = false;
							Lusion en = new Lusion ((Game.player.getX()+(16*16)),Game.player.getY(),16,32,Entity.LUSION_EN);
							Game.entities.add(en);
							Game.lusions.add(en);
							
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv10BiaLusionEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10BiaLusion){
							
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							Game.gameState = "NORMAL";
							
							Quadro.check = 1;
							Botão.apertarBotão();
							Lusion.spawnLusion = false;
							Lusion en = new Lusion ((Game.player.getX()+(16*16)),Game.player.getY(),16,32,Entity.LUSION_EN);
							Game.entities.add(en);
							Game.lusions.add(en);
							
							
						}
						
					}
			}
			}
			
		}
		if(frases == true && curText == 4 && Game.CUR_LEVEL == 3 && Game.setZone == 4) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv10BiaSallyPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10BiaSally){
							
							Tranzi.setTranzi = 2;
							Tranzi.StartArt();
							Game.gameState = "TRANZI";
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.endTheme();
							
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv10BiaSallyEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10BiaSally){
							
							Tranzi.setTranzi = 2;
							Tranzi.StartArt();
							Game.gameState = "TRANZI";
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.endTheme();
							
							
						}
						
					}
			}
			}
			
		}
		
		/// zone 5
		if(frases == true && curText == 6 && Game.CUR_LEVEL == 0 && Game.setZone == 5) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextZone5PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextZone5){
							Game.player.y+=4;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextZone5EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextZone5){
							Game.player.y+=4;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 0 && Game.setZone == 5) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv10PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Lili.LiliCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv10EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Lili.LiliCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 4 && Game.CUR_LEVEL == 0 && Game.setZone == 5) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv10DPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10D){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							curText = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv10DEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv10D){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							curText = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
	
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 1 && Game.setZone == 5) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv11PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv11){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Muriel.MurielCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv11EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv11){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Muriel.MurielCheck = false;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 2 && Game.setZone == 5) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv12PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv12){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							Anima.suportSpawn = true;
							Muriel.MurielCheck = false;
							Game.gameState = "ANIMA";
							Anima.Animation();
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv12EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv12){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							Anima.suportSpawn = true;
							Muriel.MurielCheck = false;
							Game.gameState = "ANIMA";
							Anima.Animation();
							
						}
						
					}
			}
			}
			
		}
		
		if(Game.gameState == "TEXT" && frases == true && curText == 1 && Game.CUR_LEVEL == 2 && Game.setZone == 5) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv12PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv12){
							Game.gameState = "ANIMA";
							Anima.aboboraSpawn = true;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							showMessage = false;
							interage = false;
							curText = 40;
							xArte = 0;
							Anima.Animation();
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv12EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv12){
							Game.gameState = "ANIMA";
							Anima.aboboraSpawn = true;
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							showMessage = false;
							interage = false;
							curText = 40;
							xArte = 0;
							Anima.Animation();
							
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 4 && Game.CUR_LEVEL == 2 && Game.setZone == 5) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv12DPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv12D){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 6;
							Muriel.MurielCheck = false;
							Game.gameState = "ANIMA";
							Anima.setMoved = 3;
							Anima.Animation();
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv12DEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv12D){
							
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 6;
							Muriel.MurielCheck = false;
							Game.gameState = "ANIMA";
							Anima.setMoved = 3;
							Anima.Animation();
						}
						
					}
			}
			}
			
		}
		
		
		// zone 6
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 1 && Game.setZone == 6) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv13PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv13){
							
							Sound.aumentarTema = true;
							System.out.println(Game.CUR_LEVEL + " esse mesmo " + Game.setZone);
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Muriel.MurielCheck = false;
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv13EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv13){
							
							Sound.aumentarTema = true;
							System.out.println(Game.CUR_LEVEL + " esse mesmo " + Game.setZone);
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Muriel.MurielCheck = false;
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		if(Game.gameState == "TEXT" && frases == true && curText == 1 && Game.CUR_LEVEL == 2 && Game.setZone == 6) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv13PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv13){
							
							Game.spritesheetArtes = new Spritesheet("/art/"+61+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							
							Game.gameState = "TEXT";
							artAnimationEndState = false;
							xArte = 0;
							index = 0;
							curIndexMsg = 0;
							frases = true;
							showMessage = true;
							interage = true;
							curText = 3;
							ativarArte();
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv13EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv13){
							
							Game.spritesheetArtes = new Spritesheet("/art/"+61+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							
							Game.gameState = "TEXT";
							artAnimationEndState = false;
							xArte = 0;
							index = 0;
							curIndexMsg = 0;
							frases = true;
							showMessage = true;
							interage = true;
							curText = 3;
							ativarArte();
							
						}
						
					}
			}
			}
			
		}
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 2 && Game.setZone == 6) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv13BiaPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv13Bia){
							
							showMessage = true;
							arteView = true;
							curText = 1;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = true;
							interage = false;
							xArte = 0;
							
							Muriel.MurielCheck = false;
							Game.gameState = "TEXT";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv13BiaEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv13Bia){
							
							showMessage = true;
							arteView = true;
							curText = 1;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = true;
							interage = false;
							xArte = 0;
							
							Muriel.MurielCheck = false;
							Game.gameState = "TEXT";
						}
						
					}
			}
			}
			
		}
		
		if(Game.gameState == "TEXT" && frases == true && curText == 3 && Game.CUR_LEVEL == 2 && Game.setZone == 6) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv13DPT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv13D){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							
							Sound.endTheme();
							Game.CUR_LEVEL = 3;
							Game.setZone = 1;
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv13DEN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv13D){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							
							Sound.endTheme();
							Game.CUR_LEVEL = 3;
							Game.setZone = 1;
							Tranzi.setTranzi = 1;
							Game.gameState = "TRANZI";
							Tranzi.tranzition();
							
						}
						
					}
			}
			}
			
		}
		
		
		///zone 7
		
		if(Game.gameState == "TEXT" && frases == true && curText == 1 && Game.CUR_LEVEL == 3 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv14PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv14){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Game.spritesheetArtes = new Spritesheet("/art/"+14+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							Sound.aumentarTema = true;
							Creditos.frases = true;
							Creditos.curText = 1;
							Game.gameState = "CREDITOS";
							
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv14EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv14){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							xArte = 0;
							Game.spritesheetArtes = new Spritesheet("/art/"+14+".png");
							Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
							Sound.aumentarTema = true;
							Creditos.frases = true;
							Creditos.curText = 1;
							Game.gameState = "CREDITOS";
							
						}
						
					}
			}
			}
			
		}
		
		if(frases == true && curText == 2 && Game.CUR_LEVEL == 3 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itensTextlv14PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv14){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itensTextlv14EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexTextlv14){
							
							artAnimationEndState = false;
							index = 0;
							curIndexMsg = 0;
							curIndex = false;
							frases = false;
							interage = false;
							curText = 0;
							Sound.aumentarTema = true;
							Game.gameState = "NORMAL";
						}
						
					}
			}
			}
			
		}
		
		if(Game.gameState == "TEXT" && frases == true && curText == 5 && Game.CUR_LEVEL == 3 && Game.setZone == 1) {
			this.time++;
			if(this.time >= this.maxTime) {
			this.time = 0;
			if(Game.Idioma == "PT") {
				if(curIndexMsg < itenslv15PT[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv15){
							
							Game.CUR_LEVEL = 0;
							xArte = 0;
							System.exit(1);
						}
						
					}
			}else if (Game.Idioma == "EN") {
				if(curIndexMsg < itenslv15EN[index].length()) {
					curIndexMsg++;
					}else {
						if (curIndex == true) {
							index++;
							curIndexMsg = 0;
							curIndex = false;
						}
						if(index == maxIndexlv15){
							
							Game.CUR_LEVEL = 0;
							xArte = 0;
							System.exit(1);
						}
						
					}
			}
			}
			
		}
		
	}
	

	// ==========================================================================================
	// Visual dos dialogos: fundo, arte grande (com transicao suave), retratos, caixa e texto
	// ==========================================================================================
	private Image arteImg, arteImgAnt;
	private float artePos = 0f;        // 0 = escondida, 1 = totalmente visivel
	private float arteTroca = 0f;      // 1 -> 0: dissolve da arte antiga para a nova
	private float arteTempo = 0f;      // segundos com a arte visivel (zoom lento)
	private boolean arteChamada = false, arteChamadaAnt = false;
	private boolean arteSaindo = false;
	private long ultimoRender = 0;
	private boolean modoCaixa = false;
	private Image retratoAtual;
	private long retratoInicio;
	private boolean retratoFrame = false;
	private float dtRender = 0.016f;
	private BufferedImage fundoCache, vinhetaArte;
	
	private static float suaveSaida(float a) { // ease-out cubico
		a = Math.max(0f, Math.min(1f, a));
		return 1f - (1f - a)*(1f - a)*(1f - a);
	}
	
	private void inicioRender() {
		long agora = System.nanoTime();
		float dt = (agora - ultimoRender)/1e9f;
		if(ultimoRender == 0 || dt > 0.25f) {
			// o texto ficou um tempo sem ser desenhado: recomeca limpo
			artePos = 0f; arteChamadaAnt = false; arteTroca = 0f; arteTempo = 0f; arteImgAnt = null;
			dt = 0.016f;
		}
		ultimoRender = agora;
		dtRender = Math.min(dt, 0.05f);
		modoCaixa = false;
		arteChamada = false;
		retratoFrame = false;
		
		boolean alvo = arteChamadaAnt && !artAnimationEndState;
		arteSaindo = !alvo;
		if(alvo) {
			artePos = Math.min(1f, artePos + dtRender/0.9f);   // entrada: ~0,9 s
			arteTempo += dtRender;
		}else {
			artePos = Math.max(0f, artePos - dtRender/0.55f);  // saida: ~0,55 s
			if(artePos <= 0f) { arteTempo = 0f; arteImgAnt = null; }
		}
		if(arteTroca > 0f) arteTroca = Math.max(0f, arteTroca - dtRender/0.6f);
	}
	
	private void fimRender(Graphics g0) {
		arteChamadaAnt = arteChamada;
		if(!retratoFrame) retratoAtual = null;
		// indicador de "avancar" (pisca)
		Graphics2D g = (Graphics2D) g0;
		int cw = Game.WIDTH*Game.SCALE, ch = Game.HEIGHT*Game.SCALE;
		float pulso = 0.5f + 0.5f*(float)Math.sin(System.nanoTime()/1e9*4.0);
		int a = (int)(110 + 120*pulso);
		int bx = cw - 70, by = ch - 34 + (int)(2*pulso);
		g.setColor(new Color(150, 255, 90, a));
		g.fillPolygon(new int[]{bx, bx, bx + 10}, new int[]{by - 7, by + 7, by}, 3);
		Object aa = g.getRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING);
		g.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
		g.setColor(new Color(220, 235, 215, a));
		g.drawString("Z", bx - 16, by + 5);
		if(aa != null) g.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, aa);
	}
	
	private void fundoCena(Graphics g0) {
		Graphics2D g = (Graphics2D) g0;
		int cw = Game.WIDTH*Game.SCALE, ch = Game.HEIGHT*Game.SCALE;
		if(frases == true) {
			if(curText == 1 || curText == 3 || curText == 5) {
				// cena de arte: preto com um leve clarao ao fundo e vinheta
				if(fundoCache == null || fundoCache.getWidth() != cw || fundoCache.getHeight() != ch) {
					fundoCache = new BufferedImage(cw, ch, BufferedImage.TYPE_INT_RGB);
					Graphics2D b = fundoCache.createGraphics();
					b.setColor(Color.black);
					b.fillRect(0, 0, cw, ch);
					b.setPaint(new java.awt.RadialGradientPaint(cw/2f, ch*0.42f, cw*0.62f, new float[]{0f, 0.55f, 1f},
							new Color[]{new Color(30, 42, 36), new Color(10, 14, 13), new Color(0, 0, 0)}));
					b.fillRect(0, 0, cw, ch);
					b.dispose();
				}
				g.drawImage(fundoCache, 0, 0, null);
			}
			if(curText == 2 || curText == 4 || curText == 6) {
				// dialogo em cima do jogo: escurece o jogo e cria faixas de cinema (cima e baixo)
				g.setColor(new Color(0,0,0,100));
				g.fillRect(0, 0, cw, ch);
				g.setPaint(new java.awt.GradientPaint(0, 0, new Color(0,0,0,170), 0, 110, new Color(0,0,0,0)));
				g.fillRect(0, 0, cw, 110);
			}
		}
		desenharArteCentral(g);
	}
	
	/** chamado pelos pontos do texto que antes desenhavam a arte: so avisa que a arte deve estar visivel */
	private void pedirArte() {
		arteChamada = true;
		Image atual = Artes.ARTS0;
		if(atual != arteImg) {
			if(arteImg != null && artePos > 0.3f) {
				arteImgAnt = arteImg;
				arteTroca = 1f;      // dissolve suave entre as duas artes
			}
			arteImg = atual;
		}
	}
	
	// moldura + sombra + arte + vinheta ja compostas uma vez so (por imagem), sobre o fundo da cena
	private BufferedImage arteComp, arteCompAnt;
	private Image arteCompFonte, arteCompAntFonte;
	private static final int MARGEM = 24;
	
	private BufferedImage comporArte(Image img) {
		int w = Game.ART_WIDTH, h = Game.ART_HEIGHT, m = MARGEM;
		int cw = Game.WIDTH*Game.SCALE, ch = Game.HEIGHT*Game.SCALE;
		int x = (cw - w)/2, y = ch/2 - h/2 - 30;
		BufferedImage c = new BufferedImage(w + 2*m, h + 2*m, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = c.createGraphics();
		// fundo da cena por tras (para a sombra se misturar certo)
		if(fundoCache != null) g.drawImage(fundoCache, -(x - m), -(y - m), null);
		// sombra suave e brilho verde discreto
		for(int i = 1; i <= 7; i++) {
			g.setColor(new Color(0, 0, 0, 30));
			g.fillRect(m - i*3, m - i*3 + 6, w + i*6, h + i*6);
		}
		g.setColor(new Color(110, 255, 60, 26));
		g.fillRect(m - 8, m - 8, w + 16, h + 16);
		g.drawImage(img, m, m, w, h, null);
		// vinheta
		if(vinhetaArte == null) {
			vinhetaArte = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D b = vinhetaArte.createGraphics();
			b.setPaint(new java.awt.RadialGradientPaint(w/2f, h/2f, (float)Math.hypot(w/2.0, h/2.0), new float[]{0f, 0.68f, 1f},
					new Color[]{new Color(0,0,0,0), new Color(0,0,0,0), new Color(0,0,0,150)}));
			b.fillRect(0, 0, w, h);
			b.setPaint(new java.awt.GradientPaint(0, h - 90, new Color(0,0,0,0), 0, h, new Color(0,0,0,120)));
			b.fillRect(0, h - 90, w, 90);
			b.dispose();
		}
		g.drawImage(vinhetaArte, m, m, null);
		// moldura
		g.setColor(new Color(120, 255, 70, 90));
		g.drawRect(m - 2, m - 2, w + 3, h + 3);
		g.setColor(new Color(235, 255, 225, 170));
		g.drawRect(m - 1, m - 1, w + 1, h + 1);
		g.setColor(new Color(150, 255, 90));
		int k = 28;
		g.fillRect(m - 4, m - 4, k, 3); g.fillRect(m - 4, m - 4, 3, k);
		g.fillRect(m + w + 4 - k, m - 4, k, 3); g.fillRect(m + w + 1, m - 4, 3, k);
		g.fillRect(m - 4, m + h + 1, k, 3); g.fillRect(m - 4, m + h + 4 - k, 3, k);
		g.fillRect(m + w + 4 - k, m + h + 1, k, 3); g.fillRect(m + w + 1, m + h + 4 - k, 3, k);
		g.dispose();
		return c;
	}
	
	private void desenharArteCentral(Graphics2D g) {
		if(arteImg == null || artePos <= 0.001f) return;
		if(arteComp == null || arteCompFonte != arteImg) {
			if(arteComp != null && arteTroca > 0f) { arteCompAnt = arteComp; arteCompAntFonte = arteCompFonte; }
			arteComp = comporArte(arteImg);
			arteCompFonte = arteImg;
		}
		float e = suaveSaida(artePos);
		int cw = Game.WIDTH*Game.SCALE, ch = Game.HEIGHT*Game.SCALE;
		int w = Game.ART_WIDTH, h = Game.ART_HEIGHT;
		int x = (cw - w)/2 + (int)((arteSaindo ? 1 : -1)*(1f - e)*46) - MARGEM;   // desliza e para
		int y = ch/2 - h/2 - 30 - MARGEM;
		boolean cruzando = arteTroca > 0f && arteCompAnt != null;
		if(!cruzando) arteCompAnt = null;
		if(e >= 0.999f && !cruzando) {
			g.drawImage(arteComp, x, y, null);   // regime normal: copia opaca, bem rapida
			return;
		}
		java.awt.Composite compAntigo = g.getComposite();
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, e));
		if(cruzando) {
			g.drawImage(arteCompAnt, x, y, null);
			g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, e*(1f - arteTroca)));
		}
		g.drawImage(arteComp, x, y, null);
		g.setComposite(compAntigo);
	}
	
	// retratos ja redimensionados (antes eram redimensionados a cada quadro)
	private final java.util.IdentityHashMap<Image, BufferedImage> retratosCache = new java.util.IdentityHashMap<Image, BufferedImage>();
	
	private BufferedImage retratoPronto(Image img) {
		BufferedImage r = retratosCache.get(img);
		if(r == null) {
			r = new BufferedImage(Game.PERSF_WIDTH, Game.PERSF_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			Graphics2D b = r.createGraphics();
			b.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			b.drawImage(img, 0, 0, Game.PERSF_WIDTH, Game.PERSF_HEIGHT, null);
			b.dispose();
			if(retratosCache.size() > 12) retratosCache.clear();
			retratosCache.put(img, r);
		}
		return r;
	}
	
	private void desenharRetrato(Graphics g0, Image img, int y) {
		Graphics2D g = (Graphics2D) g0;
		long agora = System.nanoTime();
		if(img != retratoAtual) {
			retratoAtual = img;
			retratoInicio = agora;
		}
		retratoFrame = true;
		float p = Math.min(1f, (agora - retratoInicio)/4e8f);   // entrada em 0,4 s
		float e = suaveSaida(p);
		int x = 30 - (int)((1f - e)*50);
		int bob = (int)Math.round(Math.sin(agora/1e9*1.7)*2.0);  // respiracao leve
		java.awt.Composite compAntigo = g.getComposite();
		// sombra no chao
		g.setColor(new Color(0, 0, 0, (int)(110*e)));
		g.fillOval(x + Game.PERSF_WIDTH/2 - 110, y + Game.PERSF_HEIGHT - 12, 220, 22);
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, e));
		g.drawImage(retratoPronto(img), x, y + bob, null);
		g.setComposite(compAntigo);
	}
	
	private void desenharCaixa(Graphics g0) {
		Graphics2D g = (Graphics2D) g0;
		modoCaixa = true;
		int cw = Game.WIDTH*Game.SCALE, ch = Game.HEIGHT*Game.SCALE;
		int y0 = ch - 200;
		float pulso = 0.5f + 0.5f*(float)Math.sin(System.nanoTime()/1e9*2.5);
		g.setPaint(new java.awt.GradientPaint(0, y0 - 46, new Color(0, 0, 0, 0), 0, y0 + 26, new Color(0, 0, 0, 238)));
		g.fillRect(0, y0 - 46, cw, 72);
		g.setColor(Color.black);
		g.fillRect(0, y0 + 26, cw, ch - y0 - 26);
		// fio de luz no topo da caixa
		g.setPaint(new java.awt.GradientPaint(0, 0, new Color(120, 255, 70, 0), cw/2f, 0, new Color(120, 255, 70, (int)(120 + 80*pulso)), true));
		g.fillRect(0, y0 + 26, cw, 2);
	}
	
	private void drawTxt(Graphics g0, String texto, int x, int y) {
		Graphics2D g = (Graphics2D) g0;
		int ch = Game.HEIGHT*Game.SCALE;
		boolean nome = (y == ch - 90) || (y == ch - 60 && !modoCaixa);
		Object aa = g.getRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING);
		g.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if(nome) {
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19));
			int tw = g.getFontMetrics().stringWidth(texto);
			g.setColor(new Color(0, 0, 0, 220));
			g.drawString(texto, x + 2, y + 2);
			g.setColor(new Color(160, 255, 105));
			g.drawString(texto, x, y);
			if(tw > 0) {
				g.setPaint(new java.awt.GradientPaint(x, 0, new Color(130, 255, 70, 200), x + tw + 60, 0, new Color(130, 255, 70, 0)));
				g.fillRect(x, y + 6, tw + 60, 2);
			}
		}else {
			g.setFont(Game.FONT_18);
			g.setColor(new Color(0, 0, 0, 220));
			g.drawString(texto, x + 2, y + 2);
			g.setColor(new Color(240, 244, 240));
			g.drawString(texto, x, y);
		}
		if(aa != null) g.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, aa);
	}
	
	public void render(Graphics g) {
		inicioRender();
		renderConteudo(g);
		fimRender(g);
	}
	
	private void renderConteudo(Graphics g) {
		fundoCena(g);
		
		if(Game.setZone == 1 && Game.CUR_LEVEL == 0 && curText == 1) {
		if(arteView == true) {
		pedirArte();
		}
		g.setColor(Color.white);
		g.setFont(Game.FONT_18);
		if(Game.Idioma == "PT") {
			drawTxt(g, itensPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
		}else if (Game.Idioma == "EN") {
			drawTxt(g, itensEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
		}
		drawTxt(g, itensNome[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
		
		}
		
		if(Game.setZone == 1 && Game.CUR_LEVEL == 0 && curText == 2) {
			desenharCaixa(g);
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, itensTextNome[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensTextPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensTextEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		
		if(Game.setZone == 1 && Game.CUR_LEVEL == 1 && curText == 2) {
			desenharCaixa(g);
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, kalhikoNome[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, kalhikoPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, kalhikoEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		if(Game.setZone == 1 && Game.CUR_LEVEL == 1 && curText == 4) {
			desenharCaixa(g);
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, kalhikoNome2[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, kalhiko2PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, kalhiko2EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		if(Game.setZone == 1 && Game.CUR_LEVEL == 1 && curText == 6) {
			desenharCaixa(g);
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, kalhikoNome3[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, kalhiko3PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, kalhiko3EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		
		if(Game.setZone == 2 && Game.CUR_LEVEL == 0 && curText == 1) {
			pedirArte();
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensZone1PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensZone1EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomeZone1[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		
		if(Game.setZone == 2 && Game.CUR_LEVEL == 1 && curText == 2) {
			if(itensTextArtlv2[index] == "KaykyB") {
				desenharRetrato(g, Artes.ARTKB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv2[index] == "KaykyC") {
				desenharRetrato(g, Artes.ARTKC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv2[index] == "KaykyN") {
				desenharRetrato(g, Artes.ARTKN, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv2[index] == "DujhinN") {
				desenharRetrato(g, Artes.ARTDN, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
			
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, itensTextNomelv2[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensTextlv2PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensTextlv2EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		
		if(Game.setZone == 2 && Game.CUR_LEVEL == 2 && curText == 1) {
			pedirArte();
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv3PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv3EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv3[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
		if(Game.setZone == 2 && Game.CUR_LEVEL == 2 && curText == 3) {
			if(index == 0) {
				ativarArte();
			}
			if(arteView == true && curArte == true) {
			pedirArte();
			}
			if(index == itenslv3deathPT.length-2) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv3deathPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv3deathEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv3death[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
		
		if(Game.setZone == 3 && Game.CUR_LEVEL == 0 && curText == 1) {
			if(index == 0) {
				ativarArte();
			}
			if(arteView == true && curArte == true) {
			pedirArte();
			}
			if(index == itenslv4PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv4PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv4EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv4[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
		
		if(Game.setZone == 3 && Game.CUR_LEVEL == 1 && curText == 1) {
			if(index == 0) {
				ativarArte();
			}
			if(arteView == true && curArte == true) {
			pedirArte();
			}
			if(index == itenslv5PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv5PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv5EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv5[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
		
		if(Game.setZone == 3 && Game.CUR_LEVEL == 2 && curText == 3) {
			if(index == 0) {
				ativarArte();
			}
			if(arteView == true && curArte == true) {
			pedirArte();
			}
			if(index == itenslv6PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv6PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv6EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv6[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
		
		if(Game.setZone == 4 && (Game.CUR_LEVEL == 0 && curText == 1)) {
			if(index == 0) {
				curArte = false;
				arteView = false;
				xArte = 0;
			}
			if(index == 2) {
				ativarArte();
				//arteView = true;
			}
			if(arteView == true && curArte == true) {
			pedirArte();
			}
			
			if(index == itenslv7PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv7PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv7EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv7[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		
		if(Game.CUR_LEVEL == 2 && curText == 1 && Game.setZone == 4) {
			if(arteView == true) {
			pedirArte();
			}
			if(index == itenslv8PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv8PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv8EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv8[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		if(Game.CUR_LEVEL == 2 && curText == 3 && Game.setZone == 4) {
			if(arteView == true) {
			pedirArte();
			}
			if(index == itenslv9PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv9PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv9EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}			
			drawTxt(g, itensNomelv9[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		
		// zone Bia
		if(Game.CUR_LEVEL == 2 && curText == 1 && Game.setZone == 1) {
			if(arteView == true) {
			pedirArte();
			}
			if(index == itenslv10PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv10PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv10EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv10[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		if(Game.CUR_LEVEL == 3 && curText == 2 && Game.setZone == 2) {
			if(itensTextArtlv10Bia1[index] == "BiaB") {
				desenharRetrato(g, Artes.ARTBB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10Bia1[index] == "BiaC") {
				desenharRetrato(g, Artes.ARTBC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10Bia1[index] == "BiaN") {
				desenharRetrato(g, Artes.ARTBN, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10Bia1[index] == "BiaM") {
				desenharRetrato(g, Artes.ARTBM, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
				g.setColor(Color.white);
				g.setFont(Game.FONT_18);
				drawTxt(g, itensTextNomelv10Bia1[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
				if(Game.Idioma == "PT") {
					drawTxt(g, itensTextlv10Bia1PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}else if (Game.Idioma == "EN") {
					drawTxt(g, itensTextlv10Bia1EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}
			}
		if(Game.CUR_LEVEL == 3 && curText == 2 && Game.setZone == 3) {
			if(itensTextArtlv10Bia2[index] == "BiaB") {
				desenharRetrato(g, Artes.ARTBB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10Bia2[index] == "BiaC") {
				desenharRetrato(g, Artes.ARTBC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10Bia2[index] == "BiaN") {
				desenharRetrato(g, Artes.ARTBN, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10Bia2[index] == "BiaM") {
				desenharRetrato(g, Artes.ARTBM, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10Bia2[index] == "BiaS") {
				desenharRetrato(g, Artes.ARTBS, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
				g.setColor(Color.white);
				g.setFont(Game.FONT_18);
				drawTxt(g, itensTextNomelv10Bia2[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
				if(Game.Idioma == "PT") {
					drawTxt(g, itensTextlv10Bia2PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}else if (Game.Idioma == "EN") {
					drawTxt(g, itensTextlv10Bia2EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}
			}
		if(Game.CUR_LEVEL == 3 && curText == 1 && Game.setZone == 4) {
			if(arteView == true) {
			pedirArte();
			}
			if(index == itensTextlv10BiaQuadroPT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensTextlv10BiaQuadroPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensTextlv10BiaQuadroEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensTextNomelv10BiaQuadro[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		if(Game.CUR_LEVEL == 3 && curText == 2 && Game.setZone == 4) {
			if(itensTextArtlv10BiaLusion[index] == "BiaB") {
				desenharRetrato(g, Artes.ARTBB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10BiaLusion[index] == "BiaC") {
				desenharRetrato(g, Artes.ARTBC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10BiaLusion[index] == "BiaN") {
				desenharRetrato(g, Artes.ARTBN, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10BiaLusion[index] == "BiaM") {
				desenharRetrato(g, Artes.ARTBM, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10BiaLusion[index] == "BiaS") {
				desenharRetrato(g, Artes.ARTBS, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
				g.setColor(Color.white);
				g.setFont(Game.FONT_18);
				drawTxt(g, itensTextNomelv10BiaLusion[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
				if(Game.Idioma == "PT") {
					drawTxt(g, itensTextlv10BiaLusionPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}else if (Game.Idioma == "EN") {
					drawTxt(g, itensTextlv10BiaLusionEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}
			}
		if(Game.CUR_LEVEL == 3 && curText == 4 && Game.setZone == 4) {
			if(itensTextArtlv10BiaSally[index] == "BiaB") {
				desenharRetrato(g, Artes.ARTBB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10BiaSally[index] == "BiaC") {
				desenharRetrato(g, Artes.ARTBC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10BiaSally[index] == "BiaN") {
				desenharRetrato(g, Artes.ARTBN, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10BiaSally[index] == "BiaM") {
				desenharRetrato(g, Artes.ARTBM, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10BiaSally[index] == "BiaS") {
				desenharRetrato(g, Artes.ARTBS, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
				g.setColor(Color.white);
				g.setFont(Game.FONT_18);
				drawTxt(g, itensTextNomelv10BiaSally[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
				if(Game.Idioma == "PT") {
					drawTxt(g, itensTextlv10BiaSallyPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}else if (Game.Idioma == "EN") {
					drawTxt(g, itensTextlv10BiaSallyEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}
			}
		
		
		// zone 5
		if(Game.CUR_LEVEL == 0 && curText == 6 && Game.setZone == 5) {
			desenharCaixa(g);
				g.setColor(Color.white);
				g.setFont(Game.FONT_18);
				drawTxt(g, itensTextNomeZone5[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
				if(Game.Idioma == "PT") {
					drawTxt(g, itensTextZone5PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}else if (Game.Idioma == "EN") {
					drawTxt(g, itensTextZone5EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}
			}
		if(Game.CUR_LEVEL == 0 && curText == 2 && Game.setZone == 5) {
			if(itensTextArtlv10[index] == "KaykyB") {
				desenharRetrato(g, Artes.ARTKB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10[index] == "KaykyC") {
				desenharRetrato(g, Artes.ARTKC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10[index] == "KaykyN") {
				desenharRetrato(g, Artes.ARTKN, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10[index] == "LiliB") {
				desenharRetrato(g, Artes.ARTLB, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10[index] == "LiliC") {
				desenharRetrato(g, Artes.ARTLC, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10[index] == "LiliN") {
				desenharRetrato(g, Artes.ARTLN, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
				g.setColor(Color.white);
				g.setFont(Game.FONT_18);
				drawTxt(g, itensTextNomelv10[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
				if(Game.Idioma == "PT") {
					drawTxt(g, itensTextlv10PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}else if (Game.Idioma == "EN") {
					drawTxt(g, itensTextlv10EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}
			}
		if(Game.CUR_LEVEL == 0 && curText == 4 && Game.setZone == 5) {
			if(itensTextArtlv10D[index] == "KaykyC") {
				desenharRetrato(g, Artes.ARTKC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv10D[index] == "LiliB") {
				desenharRetrato(g, Artes.ARTLB, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
				g.setColor(Color.white);
				g.setFont(Game.FONT_18);
				drawTxt(g, itensTextNomelv10D[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
				if(Game.Idioma == "PT") {
					drawTxt(g, itensTextlv10DPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}else if (Game.Idioma == "EN") {
					drawTxt(g, itensTextlv10DEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}				
				
			
			}
		if(Game.CUR_LEVEL == 1 && curText == 2 && Game.setZone == 5) {
			if(itensTextArtlv11[index] == "KaykyN") {
				desenharRetrato(g, Artes.ARTKN, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv11[index] == "KaykyB") {
				desenharRetrato(g, Artes.ARTKB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv11[index] == "KaykyC") {
				desenharRetrato(g, Artes.ARTKC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv11[index] == "MurielN") {
				desenharRetrato(g, Artes.ARTMN, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv11[index] == "MurielB") {
				desenharRetrato(g, Artes.ARTMB, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
			
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, itensTextNomelv11[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensTextlv11PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensTextlv11EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		
		if(Game.CUR_LEVEL == 2 && curText == 2 && Game.setZone == 5) {
			if(itensTextArtlv12[index] == "KaykyN") {
				desenharRetrato(g, Artes.ARTKN, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv12[index] == "KaykyB") {
				desenharRetrato(g, Artes.ARTKB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv12[index] == "KaykyC") {
				desenharRetrato(g, Artes.ARTKC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv12[index] == "MurielN") {
				desenharRetrato(g, Artes.ARTMN, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv12[index] == "MurielB") {
				desenharRetrato(g, Artes.ARTMB, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv12[index] == "MurielS") {
				desenharRetrato(g, Artes.ARTMS, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
			
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, itensTextNomelv12[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensTextlv12PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensTextlv12EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		
		if(Game.CUR_LEVEL == 2 && curText == 1 && Game.setZone == 5) {
			if(arteView == true) {
			pedirArte();
			}
			if(index == itenslv12PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv12PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv12EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv12[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		
		if(Game.gameState == "TEXT" && Game.CUR_LEVEL == 2 && curText == 4 && Game.setZone == 5) {
			if(itensTextArtlv12D[index] == "MurielC") {
				desenharRetrato(g, Artes.ARTMC, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv12D[index] == "MurielB") {
				desenharRetrato(g, Artes.ARTMB, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
			
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, itensTextNomelv12D[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensTextlv12DPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensTextlv12DEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		
		
		
		/// zone 6
		if(Game.gameState == "TEXT" && Game.CUR_LEVEL == 1 && curText == 2 && Game.setZone == 6) {
			
			if(itensTextArtlv13[index] == "KaykyB") {
				desenharRetrato(g, Artes.ARTKB, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv13[index] == "KaykyC") {
				desenharRetrato(g, Artes.ARTKC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv13[index] == "MurielB") {
				desenharRetrato(g, Artes.ARTMB, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			if(itensTextArtlv13[index] == "MurielC") {
				desenharRetrato(g, Artes.ARTMC, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
				}
			desenharCaixa(g);
			
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, itensTextNomelv13[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensTextlv13PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensTextlv13EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
           if(Game.gameState == "TEXT" && Game.CUR_LEVEL == 2 && curText == 2 && Game.setZone == 6) {
			
				if(itensTextArtlv13Bia[index] == "BiaC") {
					desenharRetrato(g, Artes.ARTBC, (Game.HEIGHT*Game.SCALE)-170-Game.PERSF_HEIGHT);
					}
				if(itensTextArtlv13Bia[index] == "MurielB") {
					desenharRetrato(g, Artes.ARTMB, (Game.HEIGHT*Game.SCALE)-200-Game.PERSF_HEIGHT);
					}
				desenharCaixa(g);
				
				g.setColor(Color.white);
				g.setFont(Game.FONT_18);
				drawTxt(g, itensTextNomelv13Bia[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
				if(Game.Idioma == "PT") {
					drawTxt(g, itensTextlv13BiaPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}else if (Game.Idioma == "EN") {
					drawTxt(g, itensTextlv13BiaEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
				}
			}
           
		if(Game.CUR_LEVEL == 2 && curText == 1 && Game.setZone == 6) {
			if(arteView == true) {
			pedirArte();
			}
			if(index == itenslv13PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv13PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv13EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv13[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		
		if(Game.CUR_LEVEL == 2 && curText == 3 && Game.setZone == 6) {
			if(arteView == true) {
			pedirArte();
			}
			if(index == itenslv13DPT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv13DPT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv13DEN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv13D[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		
		//zone 7
		
		if(Game.CUR_LEVEL == 3 && curText == 1 && Game.setZone == 1) {
			if(arteView == true) {
			pedirArte();
			}
			if(index == itenslv14PT.length-1) {
				artAnimationEndState = true;
				}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv14PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv14EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv14[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		
		if(Game.gameState == "TEXT" && Game.CUR_LEVEL == 3 && curText == 2 && Game.setZone == 1) {
			desenharCaixa(g);
			
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			drawTxt(g, itensTextNomelv14[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-90);
			if(Game.Idioma == "PT") {
				drawTxt(g, itensTextlv14PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itensTextlv14EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			}
			}
		if(Game.CUR_LEVEL == 3 && curText == 5 && Game.setZone == 1) {
			if(arteView == true) {
			pedirArte();
			}
			g.setColor(Color.white);
			g.setFont(Game.FONT_18);
			if(Game.Idioma == "PT") {
				drawTxt(g, itenslv15PT[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}else if (Game.Idioma == "EN") {
				drawTxt(g, itenslv15EN[index].substring(0, curIndexMsg),((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-30);
			}
			drawTxt(g, itensNomelv15[index],((Game.WIDTH*Game.SCALE)/2)-((Game.ART_WIDTH)/2), (Game.HEIGHT*Game.SCALE)-60);
			
			}
		
		

	}
	
	
	
}