package cloud.popples.overview.dioc.newway;

import cloud.popples.overview.dioc.Knight;
import cloud.popples.overview.dioc.Quest;

public class BraveKnight implements Knight {

    private Quest quest;

    public BraveKnight (Quest quest) {
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
