package genetics

import scala.collection.mutable.ListBuffer
import scala.util.Random


object GeneticAlgorithm {

  // initial genes should be a list of random values instead
  // experiment with this value
  var GENERATIONS = 5

  /**
   * Uses a genetic algorithm to optimize a generic problem
   *
   * @param incubator     Determines how instances of type T are created from a List of Doubles (genes)
   * @param costFunction  Determines the cost for a given instance of T
   * @param numberOfGenes The size of the List expected by the incubator
   * @tparam T The type to be optimized
   * @return An instance of T with minimal cost
   */


  def geneticAlgorithm[T](incubator: List[Double] => T, costFunction: T => Double, numberOfGenes: Int): T = {

    var bestGene:List[Double] = List()

    def makeInitials(len:Int):Unit = {
      for(i <- 1 to len){
        bestGene = bestGene :+ 0.0
      }
    }

    // initial gene lists
    var genes = new ListBuffer[List[Double]]()
    var checkedGenes = new ListBuffer[List[Double]]()
    // initial best values. Should make this a list of random values
    makeInitials(numberOfGenes)
    var bestCost = costFunction(incubator(bestGene))

    def makeMutators():List[Double] = {
      var mutated:List[Double] = List()
      var counter:Double = 0
      while(counter < bestCost){
        var random = new Random
        mutated = mutated :+ (counter * random.nextDouble)
        mutated = mutated :+ (-counter * random.nextDouble)
        counter += bestCost / 10
      }
      mutated
    }

    def geneHelper(len:Int, geneList:List[Double]):ListBuffer[List[Double]] = {
      var totalGenes = new ListBuffer[List[Double]]
      for(i <- 0 to 5){
        var counter = 0
        var genome:List[Double] = List()
        for(oneGene <- 0 until numberOfGenes){
          genome = genome :+ geneList(i + counter)
          counter += 6
        }
        totalGenes += genome
      }
      totalGenes
    }


    def mutate(): Unit = {

      for (g <- 1 to GENERATIONS) {

        var random = new Random

        var mutators:List[Double] = makeMutators()

        genes.clear()
        checkedGenes.clear()

        // clear the old genes out

        // start a new generation
        genes += bestGene

        //Should be list buffer for faster access
        var newGene:List[Double] = List()


        for (n <- 1 to numberOfGenes){
          newGene = newGene :+ (bestGene(n - 1) + random.nextDouble * random.shuffle(mutators).head)
          newGene = newGene :+ (bestGene(n - 1) - random.nextDouble * random.shuffle(mutators).head)
          newGene = newGene :+ (bestGene(n - 1) + random.nextDouble + random.shuffle(mutators).head)
          newGene = newGene :+ (bestGene(n - 1) - random.nextDouble - random.shuffle(mutators).head)
          newGene = newGene :+ (bestGene(n - 1) - random.nextDouble + random.shuffle(mutators).head)
          newGene = newGene :+ (bestGene(n - 1) + random.nextDouble - random.shuffle(mutators).head)
        }
        genes = geneHelper(newGene.length, newGene)
      }
    }

    // could refactor to a separate function
    def updateGenes():Unit = {
      for (gene <- genes) {
        val animal = incubator(gene)
        val cost = costFunction(animal)
        //println("Gene: " + gene + " cost: " + cost)
        //check cost and add the smallest value in front
        if (cost < bestCost) {
          checkedGenes.prepend(gene)
          bestGene = gene
          bestCost = cost
        } else {
          checkedGenes.append(gene)
          println(checkedGenes)
        }
      }
    }

    while(bestCost > 0.01) {
      mutate()
      updateGenes()
    }

    val result = checkedGenes.head
    incubator(result)
  }
}
