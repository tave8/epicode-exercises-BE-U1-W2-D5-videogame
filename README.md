# Object Design

## Classi

```
classe Gioco (astratta)
    -- attributes
    integer final idGioco (unique)
    string titolo
    integer final annoPubblicazione
    float prezzo (> 0)
    -- getters
    getIdGioco()
    getTitolo()
    getAnnoPubblicazione()
    getPrezzo()
    -- setters
    setTitolo()
    setPrezzo()

classe Videogioco (concreta) estende Gioco 
    PiattaformaVideogioco piattaforma
    integer durataGioco
    GenereVideogioco genere
    -- getters
    getPiattaforma()
    getDurataGioco()
    getGenere()
    -- setters
    setPiattaforma()
    setDurataGioco()
    setGenere()

classe GiocoDaTavolo (concreta) estende Gioco
    integer numeroGiocatori (between 2 and 10)
    integer durataMediaPartita
    -- getters
    getNumeroGiocatori()
    getDurataMediaPartita()
    -- setters
    setNumeroGiocatori()
    setDurataMediaPartita()
  
classe Collezione (astratta) 
    astratto items
    astratto add()
    astratto findById()
    astratto removeById()
    astratto updateById()

classe CollezioneGiochi estende Collezione throws GiocoIdIsNotUniqueException
    -- attributes
    
    -- instance methods
    
    -- class methods
    boolean isIdUnique()
      

```

## Enums

```
GenereVideogioco
    ACTION
    WAR
    SCIFI

PiattaformaVideogioco
    PC
    PS5
    XBOX
```

## Eccezioni

```
GiocoIdIsNotUniqueException estende RuntimeException


```

