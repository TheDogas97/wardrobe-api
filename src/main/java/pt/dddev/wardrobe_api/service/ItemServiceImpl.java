package pt.dddev.wardrobe_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pt.dddev.wardrobe_api.dto.ItemDTO;
import pt.dddev.wardrobe_api.entity.Item;
import pt.dddev.wardrobe_api.mapper.ItemDTOMapper;
import pt.dddev.wardrobe_api.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository;
    ItemDTOMapper itemDTOMapper;

    private ItemServiceImpl(ItemRepository itemRepository, ItemDTOMapper itemDTOMapper) {
        this.itemRepository = itemRepository;
        this.itemDTOMapper = itemDTOMapper;
    }

    public List<ItemDTO> addItems(List<Item> items) {
        return itemRepository
                .saveAll(items)
                .stream()
                .map(itemDTOMapper::apply)
                .toList();
    }

    public List<ItemDTO> getItems() {
        return itemRepository
                .findAll()
                .stream()
                .map(itemDTOMapper::apply)
                .toList();
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getItemById'");
    }

    public Optional<Item> removeItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        item.ifPresent(itemRepository::delete);
        return item;
    }
}