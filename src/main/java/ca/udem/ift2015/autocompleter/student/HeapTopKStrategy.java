package ca.udem.ift2015.autocompleter.student;

import ca.udem.ift2015.autocompleter.model.FrequencyTable;
import ca.udem.ift2015.autocompleter.model.TopKStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Sélection des k tokens les plus fréquents via un min-tas de taille k.
 *
 * <p>Complexité cible : O(n log k) où n = taille du vocabulaire.
 */
public class HeapTopKStrategy implements TopKStrategy {

    /**
     * TODO 7 — Retourner les {@code k} tokens les plus fréquents de {@code table},
     * triés par fréquence décroissante (à égalité : ordre lexicographique croissant).
     *
     * <p>Algorithme attendu :
     * <ol>
     *   <li>Si k ≤ 0 ou la table est vide, retourner une liste vide.</li>
     *   <li>Définir un comparateur qui trie par fréquence croissante, puis par ordre
     *       lexicographique décroissant (le « moins bon » est au sommet du tas).</li>
     *   <li>Parcourir {@code table.vocabulary()} : ajouter chaque token au min-tas ;
     *       si la taille dépasse k, retirer le sommet ({@code heap.poll()}).</li>
     *   <li>Trier le contenu du tas avec le même comparateur, puis inverser →
     *       ordre décroissant final.</li>
     * </ol>
     */
    @Override
    public List<String> topK(FrequencyTable table, int k) {

        // Vérifier les cas de base
        if (k<= 0 || table.isEmpty()) {
            return new ArrayList<>();
        }

        // Comparateur : fréquence croissante, puis ordre lexicographique décroissant
        Comparator<String> comparator = Comparator.comparingInt(table::get)
                .thenComparing(Comparator.reverseOrder());

        // Min-tas de taille k
        PriorityQueue<String> heap = new PriorityQueue<>(comparator);

        // Parcourir le vocabulaire et maintenir un tas de taille k
        for (String token : table.vocabulary()) {
            heap.add(token);
            if (heap.size() > k) {
                heap.poll();
    }}

        // Extraire les éléments du tas dans une liste
        List<String> result = new ArrayList<>(heap);

        // Trier la liste avec le même comparateur, puis inverser pour ordre décroissant
        result.sort(comparator);
        Collections.reverse(result);

        return result;

    }
}

