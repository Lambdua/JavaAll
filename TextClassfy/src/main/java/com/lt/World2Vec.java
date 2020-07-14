package com.lt;

import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.FileNotFoundException;

/**
 * @author liangtao
 * @Date 2020/7/14
 **/
@Slf4j
public class World2Vec {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "E:\\codeRepository\\JavaAll\\TextClassfy\\toutiao_cat_data\\toutiao_cat_data.txt";
        SentenceIterator iter = new BasicLineIterator(filePath);
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());
        VocabCache<VocabWord> cache = new AbstractCache<VocabWord>();
        WeightLookupTable<VocabWord> table = new InMemoryLookupTable.Builder<VocabWord>().vectorLength(100)
                .useAdaGrad(false).cache(cache).build();

        System.out.println("Building model....");
        Word2Vec vec = new Word2Vec.Builder()
                .elementsLearningAlgorithm("org.deeplearning4j.models.embeddings.learning.impl.elements.CBOW")
                .minWordFrequency(0).iterations(1).epochs(20).layerSize(100).seed(42).windowSize(8).iterate(iter)
                .tokenizerFactory(t).lookupTable(table).vocabCache(cache).build();

        vec.fit();
        WordVectorSerializer.writeWord2VecModel(vec, "E:\\codeRepository\\JavaAll\\TextClassfy\\toutiao_cat_data\\toutiao.vec");
    }
}
