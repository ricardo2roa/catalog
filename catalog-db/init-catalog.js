db = db.getSiblingDB('catalog');
db.products.insertMany([
    {
        code: 1,
        tag: 1,
        category: 1,
        brand:{
            code:1,
            name:"Acana"
        },
        name:"Fresh Water Fish",
        information:{
            benefits:"los dos principales ingredientes son trucha arcoíris cruda y bagre; denso en nutrientes y rico en proteínas. Las materias primas se congelan en su máxima frescura. Incluye frutas y verduras frescas como calabaza entera, berza, manzanas y peras. Sin ingredientes de cereales añadidos, soja, maíz, trigo o tapioca. Recubierto con hígado de bacalao liofilizado para un delicioso sabor que su perro anhelará. Fabricado en EE. UU. Con los mejores ingredientes del mundo.",
            features:"contiene trucha arco iris, bagre, harina de bagre, lentejas rojas enteras, frijoles pintos enteros, guisantes verdes enteros, aceite de bagre, lentejas verdes enteras, garbanzos enteros, guisantes amarillos enteros, perca entera, harina de pescado blanco, aceite de pescado, harina de caballa, fibra de lentejas, natural sabor a pescado, almidón de guisantes, sal, algas marinas secas, calabaza entera, berza, manzanas enteras, peras enteras, tocoferoles mixtos (conservante), proteinato de zinc, suplemento de vitamina E, pantotenato de calcio, riboflavina, ácido fólico, hígado de bacalao liofilizado, Proteinato de cobre, raíz de achicoria seca, cúrcuma, zarzaparrilla, raíz de althea, escaramujos, bayas de enebro, ácido cítrico (conservante), extracto de romero, producto de fermentación de Lactobacillus acidophilus seco, producto de fermentación de Bifidobacterium animalis seco, producto de fermentación de Lactobacillus casei seco.",
            description:"tiene una receta de pescado de agua dulce de ACANA está equilibrada con un 60% de ingredientes animales de calidad como trucha arco iris, bagre y perca, y más de un 40% de frutas, verduras y productos botánicos. En nuestra cocina nunca se agregan colorantes, sabores o conservantes artificiales."
        },
        references:[
            {
                peso: 5900,
                price: 28000.0,
                sku: "FRES-ACAN-5900-1",
                stock:7
            },
            {
                peso: 11300,
                price: 240000.0,
                sku: "FRES-ACAN-11300-1",
                stock: 15
            }
        ]
    }
]);