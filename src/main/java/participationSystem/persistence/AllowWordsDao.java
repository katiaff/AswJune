package participationSystem.persistence;

import java.util.List;

public interface AllowWordsDao {
	
	void add(List<String> words);
	List<String> findAllWords();
	boolean checkWords(String comment);
}
